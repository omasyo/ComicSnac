package com.omasyo.comicsnac.model.search

enum class SearchType(val format: String) {
    Character("character"),
    Team("team"),
    Publisher("publisher"),
    Concept("concept"),
    Location("location"),
    Object("object"),
    Person("person"),
    Volume("volume"),
    Issue("issue"),
    StoryArc("story_arc"),
}

val SearchType.label
    get() = when (this) {
        SearchType.StoryArc -> "Story Arc"
        else -> name
    }