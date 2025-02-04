package com.childhoodmemories.a80s90s.data

import com.childhoodmemories.a80s90s.data.database.UserDao
import com.childhoodmemories.a80s90s.model.User

object UserRepository {

    private val userDao = UserDao()

    var currentUser: User?
        get() = userDao.currentUser
        set(value) {
            userDao.currentUser = value
        }

    fun siginIn(email: String, password: String): Boolean {
        val users = userDao.userList + mockUserList
        val matchedUser = users.find { it.email == email }
        if (matchedUser == null) {
            throw MemoException.SignIn.InvalidEmail()
        } else if (matchedUser.password != password) {
            throw MemoException.SignIn.PasswordNotCorrect()
        } else {
            userDao.currentUser = matchedUser
            return true
        }
    }
}

val emma = User(
    id = "u001",
    firstname = "Emma",
    lastname = "Thompson",
    email = "emma.thompson@example.com",
    password = "securePass123",
    avatar = "https://cdn.britannica.com/59/244059-050-ECB2125C/Macauly-Culkin-displays-his-signature-pose-from-the-movie-Home-Alone.jpg"
)

val liam = User(
    id = "u002",
    firstname = "Liam",
    lastname = "Nguyen",
    email = "liam.nguyen@example.com",
    password = "nguyen2023!",
    avatar = "https://www.leparisien.fr/resizer/62CUaU8mQglwksQz5Z3MfW2_hPo=/932x582/cloudfront-eu-central-1.images.arcpublishing.com/leparisien/MRCRRNPS6F3KMIW5XICJN6LW2Y.jpg"
)

val sophia = User(
    id = "u003",
    firstname = "Sophia",
    lastname = "Rodriguez",
    email = "sophia.r@example.com",
    password = "sophiaPass456",
    avatar = "https://static1.moviewebimages.com/wordpress/wp-content/uploads/2023/07/lightx-5.jpeg?q=50&fit=crop&w=1140&h=&dpr=1.5"
)

val ethan = User(
    id = "u004",
    firstname = "Ethan",
    lastname = "Chen",
    email = "ethan.chen@example.com",
    password = "ethanC2023",
    avatar = "https://avatars.fastly.steamstatic.com/ab44d1b6f4e2fa29c1a3be0316c7ce6259f17a26_full.jpg"
)

val olivia = User(
    id = "u005",
    firstname = "Olivia",
    lastname = "Patel",
    email = "o.patel@example.com",
    password = "olivia1967!",
    avatar = "https://example.com/avatars/olivia.jpg"
)

val noah = User(
    id = "u006",
    firstname = "Noah",
    lastname = "Williams",
    email = "noah.w@example.com",
    password = "noahChef31",
    avatar = null
)

val ava = User(
    id = "u007",
    firstname = "Ava",
    lastname = "Johnson",
    email = "ava.johnson@example.com",
    password = "avaNurse25",
    avatar = "https://example.com/avatars/ava.png"
)

val mason = User(
    id = "u008",
    firstname = "Mason",
    lastname = "Lee",
    email = "mason.lee@example.com",
    password = "masonAcc47!",
    avatar = "https://preview.redd.it/8nei74fcy9n71.png?auto=webp&s=a9b2d47288a86d41bd2344457b5cff7d881b4c16"
)

val isabella = User(
    id = "u009",
    firstname = "Isabella",
    lastname = "Garcia",
    email = "i.garcia@example.com",
    password = "bellaWriter39",
    avatar = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/7fea4f5c-13d3-4fb8-8bd9-3c50cd69e61e/ddxzg3c-87b57d67-fb75-4c58-99f5-51671972af88.jpg/v1/fill/w_894,h_894,q_70,strp/rocky_balboa_by_thedeathlyfollows_ddxzg3c-pre.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9MTA4MCIsInBhdGgiOiJcL2ZcLzdmZWE0ZjVjLTEzZDMtNGZiOC04YmQ5LTNjNTBjZDY5ZTYxZVwvZGR4emczYy04N2I1N2Q2Ny1mYjc1LTRjNTgtOTlmNS01MTY3MTk3MmFmODguanBnIiwid2lkdGgiOiI8PTEwODAifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6aW1hZ2Uub3BlcmF0aW9ucyJdfQ.4Lw54MW0CoYTt6QoX-ypQnZZ4RyfnygBsRGnJFUL4Zc"
)

val jackson = User(
    id = "u010",
    firstname = "Jackson",
    lastname = "Brown",
    email = "j.brown@example.com",
    password = "jackArch52",
    avatar = null
)

val mockUserList = listOf(
    emma,
    liam,
    sophia,
    ethan,
    olivia,
    noah,
    ava,
    mason,
    isabella,
    jackson
)