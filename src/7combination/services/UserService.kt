package `7combination`.services

data class User(val name: String, val age: Int)

class UserService {
    private val users = mutableListOf(
        User("test", 2),
        User("test2", 3)
    )

    fun allUsers() = users

    fun saveUser(user: User) = users.add(user)

    fun getUserByName(name: String) = users.find { it.name == name }
}