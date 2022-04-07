import junit.framework.Assert.assertTrue
import org.junit.Test
import kotlin.Nothing as Nothing

class WallServiceTest {

    @Test(expected = PostNotFoundException::class)  // Вызов функции без исключения см. в следующем тесте
    fun shouldThrow() {
        val comments1 = Post.Comments(100, false, false, false, false)
        val comments2 = Post.Comments(1, true, true, true, true)
        val copiright1 = Post.Copyright(2556, "Ivanych", "Petr", "user")
        val copiright2 = Post.Copyright(3635, "OneJournal99", "OneJournal", "journal")
        val likes1 = Post.Likes(25, false, false, false)
        val likes2 = Post.Likes(25, true, true, true)
        val reposts1 = Post.Reposts(10, false)
        val reposts2 = Post.Reposts(1, true)
        val views1 = Post.View(10)
        val views2 = Post.View(0)
        val placeholder = Post.Donut.Placeholder("Заглушка");
        val donut1 = Post.Donut(false, 200, placeholder, false, "duration")
        val donut2 = Post.Donut(true, 100, placeholder, true, "all")

        val geo1 = Post.Geo("town", "32.36, 25.45", null)
        val geo2 = Post.Geo("sea", "45.00, 32.12", null)

        val film1: Post.Attachment = Post.VideoAttachment(12, 10, 111, 235,
            "Kill Bill", "Tarantino", 2003);
        val film2: Post.Attachment = Post.VideoAttachment(115, 23, 1023, 11,
            "Limit Less", "Neil Burger", 2011);
        val photo1: Post.Attachment = Post.PhotoAttachment(234, 345, 446, 5678)
        val photo2: Post.Attachment = Post.PhotoAttachment(3455, 12, 2, 333)
        val song1: Post.Attachment = Post.AudioAttachment(3434, 56, 345345, 453554,
            "Smoke on the water", "Deep Purple", "All group", "All group")

        var attachments1: Array<Post.Attachment> = Array(3) {film1; photo1; song1};
        var attachments2: Array<Post.Attachment> = Array(5) {film1; film2; photo1; photo2; song1};

        val service = WallService

        service.add(Post(0, 3535, 4444, 8989, 456567788, "Текст поста 1",
            5555, 6767, false, comments1, copiright1, likes1, reposts1, views1,
            "post", 3456, false, false, false, false, false,
            false, donut1, 3456, null, geo1, null, attachments1
        ))
        service.add(Post(0, 4567, 3567, 4356, 345678956, "Текст поста 2",
            4567, 5467, true, comments2, copiright2, likes2, reposts2, views2,
            "copy", 9876, true, true, true, true, true,
            true, donut2, 4567, null, geo2, null, attachments2
        ))

        // Выбрасывает исключение
        service.createComment(Post.Comment(-1, 100, 0, "Хороший пост",
            2335, null, 125u, 0))
    }

    @Test
    fun updateExistingTruePost() {
        val comments1 = Post.Comments(100, false, false, false, false)
        val comments2 = Post.Comments(1, true, true, true, true)
        val copiright1 = Post.Copyright(2556, "Ivanych", "Petr", "user")
        val copiright2 = Post.Copyright(3635, "OneJournal99", "OneJournal", "journal")
        val likes1 = Post.Likes(25, false, false, false)
        val likes2 = Post.Likes(25, true, true, true)
        val reposts1 = Post.Reposts(10, false)
        val reposts2 = Post.Reposts(1, true)
        val views1 = Post.View(10)
        val views2 = Post.View(0)
        val placeholder = Post.Donut.Placeholder("Заглушка");
        val donut1 = Post.Donut(false, 200, placeholder, false, "duration")
        val donut2 = Post.Donut(true, 100, placeholder, true, "all")

        val geo1 = Post.Geo("town", "32.36, 25.45", null)
        val geo2 = Post.Geo("sea", "45.00, 32.12", null)

        val film1: Post.Attachment = Post.VideoAttachment(12, 10, 111, 235,
            "Kill Bill", "Tarantino", 2003);
        val film2: Post.Attachment = Post.VideoAttachment(115, 23, 1023, 11,
            "Limit Less", "Neil Burger", 2011);
        val photo1: Post.Attachment = Post.PhotoAttachment(234, 345, 446, 5678)
        val photo2: Post.Attachment = Post.PhotoAttachment(3455, 12, 2, 333)
        val song1: Post.Attachment = Post.AudioAttachment(3434, 56, 345345, 453554,
            "Smoke on the water", "Deep Purple", "All group", "All group")

        var attachments1: Array<Post.Attachment> = Array(3) {film1; photo1; song1};
        var attachments2: Array<Post.Attachment> = Array(5) {film1; film2; photo1; photo2; song1};

        val service = WallService

        service.add(Post(0, 3535, 4444, 8989, 456567788, "Текст поста 1",
            5555, 6767, false, comments1, copiright1, likes1, reposts1, views1,
            "post", 3456, false, false, false, false, false,
            false, donut1, 3456, null, geo1, null, attachments1
        ))
        service.add(Post(0, 4567, 3567, 4356, 345678956, "Текст поста 2",
            4567, 5467, true, comments2, copiright2, likes2, reposts2, views2,
            "copy", 9876, true, true, true, true, true,
            true, donut2, 4567, null, geo2, null, attachments2
        ))

        // Работает корректно - не выбрасывает исключение
        service.createComment(Post.Comment(-1, 1, 0, "Хороший пост",
            2335, null, 125u, 0))

        val update = Post(1, 3535, 4444, 8989, 456567788, "Текст поста 1",
            5555, 6767, false, comments1, copiright1, likes1, reposts1, views1,
            "post", 3456, false, false, false, false, false,
            false, donut1, 3456, null, geo1, null, attachments1)

        val result = service.update(update)

        assertTrue(result)
    }


    @Test
    fun updateExistingTrueCopy() {
        val comments1 = Post.Comments(100, false, false, false, false)
        val comments2 = Post.Comments(1, true, true, true, true)
        val copiright1 = Post.Copyright(2556, "Ivanych", "Petr", "user")
        val copiright2 = Post.Copyright(3635, "OneJournal99", "OneJournal", "journal")
        val likes1 = Post.Likes(25, false, false, false)
        val likes2 = Post.Likes(25, true, true, true)
        val reposts1 = Post.Reposts(10, false)
        val reposts2 = Post.Reposts(1, true)
        val views1 = Post.View(10)
        val views2 = Post.View(0)
        val placeholder = Post.Donut.Placeholder("Заглушка");
        val donut1 = Post.Donut(false, 200, placeholder, false, "duration")
        val donut2 = Post.Donut(true, 100, placeholder, true, "all")

        val geo1 = Post.Geo("town", "32.36, 25.45", null)
        val geo2 = Post.Geo("sea", "45.00, 32.12", null)

        val film1: Post.Attachment = Post.VideoAttachment(12, 10, 111, 235,
            "Kill Bill", "Tarantino", 2003);
        val film2: Post.Attachment = Post.VideoAttachment(115, 23, 1023, 11,
            "Limit Less", "Neil Burger", 2011);
        val photo1: Post.Attachment = Post.PhotoAttachment(234, 345, 446, 5678)
        val photo2: Post.Attachment = Post.PhotoAttachment(3455, 12, 2, 333)
        val song1: Post.Attachment = Post.AudioAttachment(3434, 56, 345345, 453554,
            "Smoke on the water", "Deep Purple", "All group", "All group")

        var attachments1: Array<Post.Attachment> = Array(3) {film1; photo1; song1};
        var attachments2: Array<Post.Attachment> = Array(5) {film1; film2; photo1; photo2; song1};


        val service = WallService

        service.add(Post(0, 3535, 4444, 8989, 456567788, "Текст поста 1",
            5555, 6767, false, comments1, copiright1, likes1, reposts1, views1,
            "post", 3456, false, false, false, false, false,
            false, donut1, 3456, null, geo1, null, attachments1
        ))
        service.add(Post(0, 4567, 3567, 4356, 345678956, "Текст поста 2",
            4567, 5467, true, comments2, copiright2, likes2, reposts2, views2,
            "copy", 9876, true, true, true, true, true,
            true, donut2, 4467, null, geo2, null, attachments2
        ))

        val update = Post(2, 3535, 4444, 8989, 456567788, "Текст поста 1",
            5555, 6767, false, comments1, copiright1, likes1, reposts1, views1,
            "post", 3456, false, false, false, false, false,
            false, donut1, 3456, null, geo1, null, attachments1)

        val result = service.update(update)

        assertTrue(result)
    }


    @Test
    fun updateExistingFalse() {
        val comments1 = Post.Comments(100, false, false, false, false)
        val comments2 = Post.Comments(1, true, true, true, true)
        val copiright1 = Post.Copyright(2556, "Ivanych", "Petr", "user")
        val copiright2 = Post.Copyright(3635, "OneJournal99", "OneJournal", "journal")
        val likes1 = Post.Likes(25, false, false, false)
        val likes2 = Post.Likes(25, true, true, true)
        val reposts1 = Post.Reposts(10, false)
        val reposts2 = Post.Reposts(1, true)
        val views1 = Post.View(10)
        val views2 = Post.View(0)
        val placeholder = Post.Donut.Placeholder("Заглушка");
        val donut1 = Post.Donut(false, 200, placeholder, false, "duration")
        val donut2 = Post.Donut(true, 100, placeholder, true, "all")

        val geo1 = Post.Geo("town", "32.36, 25.45", null)
        val geo2 = Post.Geo("sea", "45.00, 32.12", null)

        val film1: Post.Attachment = Post.VideoAttachment(12, 10, 111, 235,
            "Kill Bill", "Tarantino", 2003);
        val film2: Post.Attachment = Post.VideoAttachment(115, 23, 1023, 11,
            "Limit Less", "Neil Burger", 2011);
        val photo1: Post.Attachment = Post.PhotoAttachment(234, 345, 446, 5678)
        val photo2: Post.Attachment = Post.PhotoAttachment(3455, 12, 2, 333)
        val song1: Post.Attachment = Post.AudioAttachment(3434, 56, 345345, 453554,
            "Smoke on the water", "Deep Purple", "All group", "All group")

        var attachments1: Array<Post.Attachment> = Array(3) {film1; photo1; song1};
        var attachments2: Array<Post.Attachment> = Array(5) {film1; film2; photo1; photo2; song1};


        val service = WallService

        service.add(Post(0, 3535, 4444, 8989, 456567788, "Текст поста 1",
            5555, 6767, false, comments1, copiright1, likes1, reposts1, views1,
            "post", 3456, false, false, false, false, false,
            false, donut1, 3456, null, geo1, null, attachments1
        ))
        service.add(Post(0, 4567, 3567, 4356, 345678956, "Текст поста 2",
            4567, 5467, true, comments2, copiright2, likes2, reposts2, views2,
            "copy", 9876, true, true, true, true, true,
            true, donut2, 4567, null, geo2, null, attachments2
        ))

        val update = Post(1, 3535, 4444, 8989, 456567788, "Текст поста 1",
            5555, 6767, false, comments1, copiright1, likes1, reposts1, views1,
            "post", 3456, false, false, false, false, false,
            false, donut1, 3456, null, geo1, null, attachments1)

        val result = service.update(update)

        assertTrue(result)
    }
}