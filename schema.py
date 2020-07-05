import re
import sys


class Schema:
    def __init__(self):
        self.classes = []

    def add_class(self, new_class):
        self.classes.append(new_class)

    def to_kotlin(self):
        res = ""
        done = []
        for cls in self.classes:
            if cls.name in done:
                continue
            done.append(cls.name)
            res += f"data class {cls.name}(\n"
            for prop in cls.properties[:-1]:
                k_type = to_kotlin_type(prop)
                res += f"    val {prop.name}: {k_type},\n"
            last_prop = cls.properties[-1]
            k_type = to_kotlin_type(last_prop)
            res += f"    val {last_prop.name}: {k_type}\n"
            res += ")\n"
        return res


def to_kotlin_type(st):
    type_name = st.prop_type
    opt = "?" if st.optional else ""
    if type_name == "string":
        return "String" + opt
    if type_name == "integer":
        return "Int" + opt
    if type_name == "boolean":
        return "Boolean" + opt
    if type_name.startswith("array["):
        return "List<" + to_kotlin_type(SchemaProperty("", type_name.split("[")[1][:-1])) + ">" + opt

    return type_name + opt


class SchemaClass:
    def __init__(self, name, properties):
        self.properties = properties
        self.name = name

    def __str__(self) -> str:
        return '' + self.name + '{' + ', '.join([str(i) for i in self.properties]) + '}'


class SchemaProperty:
    def __init__(self, name, prop_type, optional=False):
        self.optional = optional
        self.name = name
        self.prop_type = prop_type

    def __str__(self) -> str:
        return '' + self.name + ': ' + self.prop_type


name_rx = re.compile('\\w+(?= {)')
property_rx = re.compile('(\\w+) \\((.+)\\)(?=:[ \\w]+)')


def parse_property(prop):
    name = prop[0]
    info = prop[1].split(", ")
    optional = 'optional' in info
    if optional:
        info.remove('optional')
    prop_type = info[0]
    return SchemaProperty(name, prop_type, optional)


def parse_schema(schem):
    schema = Schema()
    current_name = None
    current_props = []
    for line in schem.split("\n"):
        line = line.strip()
        if (name_match := name_rx.match(line)) is not None:
            current_name = name_match[0]
            continue

        if (property_match := property_rx.match(line)) is not None:
            prop_name = property_match[1]
            prop_type = property_match[2]
            current_props.append((prop_name, prop_type))
            continue

        if line == "}":
            parsed_props = [parse_property(prop) for prop in current_props]
            new_class = SchemaClass(current_name, parsed_props)
            schema.add_class(new_class)
            current_name = None
            current_props = []
            continue

    return schema


if __name__ == '__main__':
    if len(sys.argv) <= 1:
        print(f"Usage: {sys.argv[0]} [file.schem]")
        exit(2)
    file_name = sys.argv[1]
    file = open(file_name)
    inp = file.read()
    print(parse_schema(inp).to_kotlin())
