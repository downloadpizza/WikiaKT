# WikiaKT

These are the bindings for the Wikia API (more commonly known as Fandom Wiki)
The original API documentation can be found at [here](https://jellesmarbleruns.fandom.com/api/v1/#!/Activity/getRecentlyChangedArticles_get_1)
Or at any other site that uses Wikia with the path `/api/v1/`

# Contribution
It is unlikely that any documentation on methods will be added here, as I am staying very close to the original API. 
This is WIP and If you feel like helping, the biggest help would be if you were to implement Endpoints that I havent gotten to yet.
Example for an endpoint module:

[The activity module with its 2 methods](https://jellesmarbleruns.fandom.com/api/v1/#!/Activity)

You can find the response models in the API docs, they look like this:
```php
ActivityResponseResult {
    items (array[ActivityResponseItem]): Standard container name for element collection (list),
    basepath (string): Common URL prefix for relative URLs
}
ActivityResponseItem {
    article (integer): The ID of the article acted upon,
    user (integer): The ID of the user performing the action,
    revisionId (integer): The ID of the revision created from this event,
    timestamp (integer): The Unix timestamp (in seconds) that the revision was made
}
```
Or as a JSON schema (also found on the website):
```json
{
  "items": [
    {
      "article": "integer",
      "user": "integer",
      "revisionId": "integer",
      "timestamp": "integer"
    }
  ],
  "basepath": "string"
}
```

These are supposed to be implemented as data classes with the same name is in the API

The first schema, the not JSON one, can be pasted into a file and then converted with the `schema.py` file, which automaitcally builds kotlin data classes

After the Response Schema, you can find the parameters, for the two methods mentioned above, these are:
```
limit: Int
namespaces: String (A comma seperated list of Namespace IDs, which are Integers (e.g. "-2,0,1"))
allowDuplicates: Boolean (Not required, Default: True)
```

These parameters should be implemented as function parameters, with all weird JSON stuff replaced by canonical Kotlin code.
Example one of the methods implemented:
```kotlin
// file: endpoints/Activity.kt
class Activity(private val api: Wikia) {
    fun latestActivity(limit: Int, namespaces: List<Namespace>, allowDuplicates: Boolean = true): RequestResult<ActivityResponseResult> {
        val nsList = namespaces.joinToString(",")
        return api.get(
            "Activity/LatestActivity",
            listOf(
                "limit" to limit,
                "namespaces" to nsList,
                "allowDuplicates" to allowDuplicates
            )
        )
    }
}
```

# Implemented Modules and Endpoints

- [x] Activity
    - [x] LatesActivity
    - [x] RecentlyChangedArticles
- [x] Articles
    - [x] AsSimpleJson
    - [x] Details
    - [x] UnexplandedList
    - [x] ExpandedList
    - [x] UnexpandedMostLinked
    - [x] ExpandedMostLinked
    - [x] New
    - [x] UnexpandedPopular
    - [x] ExpandedPopular
    - [x] UnexpandedTop
    - [x] ExpandedTop
    - [x] TopByHub
- [ ] Mercury
    - [ ] WikiVariables
- [ ] Navigation
    - [ ] Data
- [ ] RelatedPages
    - [ ] List
- [ ] Search
    - [ ] UnexpandedCombined
    - [ ] ExpandedCombined
    - [ ] List
- [ ] SearchSuggestions
    - [ ] List
- [ ] User
    - [ ] Details
    - [ ] UsersByName
