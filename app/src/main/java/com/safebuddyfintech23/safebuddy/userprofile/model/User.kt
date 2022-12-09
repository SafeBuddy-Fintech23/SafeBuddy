package com.safebuddyfintech23.safebuddy.userprofile.model

/**
 * This will be used when uploading user data to the Firebase database.
 */
class User {
    var profileUrl: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var bankID: String? = null
    var location: String? = null

    // Empty constructor to be used for serialization
    constructor()

    constructor(
        profileUrl: String?,
        firstName: String?,
        lastName: String?,
        bankID: String?,
        location: String?
    ) {
        this.profileUrl = profileUrl
        this.firstName = firstName
        this.lastName = lastName
        this.bankID = bankID
        this.location = location
    }
}