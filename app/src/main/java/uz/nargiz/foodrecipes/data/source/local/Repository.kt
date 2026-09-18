package uz.nargiz.foodrecipes.data.source.local

import uz.nargiz.foodrecipes.domain.model.RecipeData
import uz.nargiz.foodrecipes.domain.model.RecipeDetail

object Repository {
    val recipeData = RecipeData(
        id = 4,
        slug = "videorecept-baoczyi_uz",
        lang = "uz",
        title = "Видеорецепт: баоцзы",
        primaryCategory = "boshqa",
        imageUrl = "https://zira.uz/wp-content/uploads/2026/04/img_9443.jpg",
        videoUrl = "https://www.youtube.com/watch?v=o8VsyFZtcGk",
        hasVideo = true
    )

    val recipe = RecipeDetail.Data(
        id = 1326,
        slug = "videoretsept-tunets-va-piyoz-pitsasi_uz",
        lang = "uz",
        title = "Videoretsept: «Tunets va piyoz» pitsasi",
        primaryCategory = "non",
        description = "«MuzaA» tayyor xamiridan tayyorlangan pitsa ko’plab uy bekalari uchun vaqtni tejaydi.",
        ingredients = listOf(
            RecipeDetail.Ingredient(type = "ingredient", amount = "1 ta", name = "tayyor qatlama xamir"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "160 gramm", name = "konservalangan tunets balig'i"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "1 ta", name = "qizil piyoz"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "100 gramm", name = "pishloq"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "ta'bga ko'ra", name = "achchiq qizil qalampir kukuni"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "1 choy qoshiq", name = "zaytun moyi"),
            RecipeDetail.Ingredient(type = "heading", amount = "", name = "pomidorli qaylasi uchun"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "300 millilitr", name = "qaynagan suv"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "200 gramm", name = "pomidor"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "1 choy qoshiq", name = "italyancha xushbo'y ziravorlar"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "100 gramm", name = "petrushka yoki kashnich"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "1 choy qoshiq", name = "zaytun moyi")
        ),
        steps = listOf(
            RecipeDetail.Step(stepNum = 1, stepLabel = "Qadam 1", text = "Idishda suvni qaynatamiz. Pomidorlarni po'sti oson ajralishi uchun bir ikki joyini kesamiz. Pomidorlarni qaynab turgan suvga solamiz.", images = listOf()),
            RecipeDetail.Step(stepNum = 2, stepLabel = "Qadam 2", text = "30 soniyadan so'ng suvini to'kib tashlab, pomidorlarni po'stidan ajratamiz. Katta bo'laklarga bo'lib, qo'l blenderida maydalaymiz.", images = listOf()),
            RecipeDetail.Step(stepNum = 3, stepLabel = "Qadam 3", text = "Pomidorli aralashmani olovga qo'yib, italyancha ziravorlar sepamiz.", images = listOf()),
            RecipeDetail.Step(stepNum = 4, stepLabel = "Qadam 4", text = "Maydalangan ko'katlar solib, zaytun moyini quyamiz. Qaylani o'rtacha olovda 10 daqiqa pishiramiz.", images = listOf()),
            RecipeDetail.Step(stepNum = 5, stepLabel = "Qadam 5", text = "Tayyor xamirga pomidor qaylasini surtamiz.", images = listOf()),
            RecipeDetail.Step(stepNum = 6, stepLabel = "Qadam 6", text = "Ustiga bir xil qilib, konservalangan tunets balig'ini qo'yib chiqamiz. Ustidan halqa shaklida to'g'ralgan piyoz qo'yib, qirg'ichdan chiqarilgan pishloq sepamiz.",images = listOf()),
            RecipeDetail.Step(stepNum = 7, stepLabel = "Qadam 7", text = "220 C darajada qizdirilgan gaz pechiga 7 daqiqaga yuboramiz. Maslahat: dasturxonga tortishdan avval zaytun moyi va achchiq qizil qalampir kukunini sepamiz. Yoqimli ishtaha! Instagram-dagi rasmiy sahifa -muza.uzb Reklama huquqi asosida", images = listOf("https://zira.uz/wp-content/uploads/2020/11/picca-iz-gotovogo-testa-muza-4-1024x683.jpg"))
        ),
        imageUrl = "https://zira.uz/wp-content/uploads/2020/11/picca-iz-gotovogo-testa-muza-4-1024x683.jpg",
        videoUrl = "https://www.youtube.com/watch?v=j4oIEZYOwSM",
        author = "Ситора Омонова",
        publishedDate = "27 Noyabr, 2020",
        url = "https://zira.uz/uz/recipe/videoretsept-tunets-va-piyoz-pitsasi/",
        createdAt = "2026-06-01 06:24:36",
        hasVideo = true
    )

    val recipe2 = RecipeDetail.Data(
        id = 956,
        slug = "batafsil-fotoretsept-koreyscha-bodring_uz",
        lang = "uz",
        title = "Batafsil fotoretsept: koreyscha bodring",
        primaryCategory = "salat",
        description = "Biroz achchiq ta’mga ega,  tez tayyorlanadigan bodringdan salat . U go’shtli taomlar bilan ajoyib uyg’unlik hosil qiladi, achchiq ta’mi esa hech kimni befarq qoldirmaydi.",
        ingredients = listOf(
            RecipeDetail.Ingredient(type = "ingredient", amount = "500 gramm", name = "bodring"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "1 ta", name = "piyoz"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "4 dona", name = "sarimsoqpiyoz tishchasi"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "50 millilitr", name = "o'simlik yog'i"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "1 bog'", name = "kashnich"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "1 choy qoshiq", name = "tuz"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "0,5 choy qoshiq", name = "qora murch"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "2 choy qoshiq", name = "shakar"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "1 choy qoshiq", name = "kashnich urug'i"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "1 choy qoshiq", name = "achchiq qizil qalampir kukuni"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "1 choy qoshiq", name = "9% li sirka"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "3 choy qoshiq", name = "soya qaylasi"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "3 choy qoshiq", name = "kunjut yog'i"),
            RecipeDetail.Ingredient(type = "ingredient", amount = "10 gramm", name = "kunjut")
        ),
        steps = listOf(
            RecipeDetail.Step(stepNum = 1, stepLabel = "Qadam 1", text = "Barcha masalliqlarni tayyorlaymiz.", images = emptyList()),
            RecipeDetail.Step(stepNum = 2, stepLabel = "Qadam 2", text = "Bodringni dumaloq shaklda to’g’raymiz, va 30 daqiqaga, suyuqligi chiqishi uchun, qoldiramiz.", images = emptyList()),
            RecipeDetail.Step(stepNum = 3, stepLabel = "Qadam 3", text = "Piyozni katta shaklda to’g’rab, o’simlik yog’ida yaxshilab qovuramiz.", images = emptyList()),
            RecipeDetail.Step(stepNum = 4, stepLabel = "Qadam 4", text = "Kashnich va sarimsoqpiyozni maydalaymiz.", images = emptyList()),
            RecipeDetail.Step(stepNum = 5, stepLabel = "Qadam 5", text = "Bodringdan ajragan suyuqlikni to’kib yuboramiz.", images = emptyList()),
            RecipeDetail.Step(stepNum = 6, stepLabel = "Qadam 6", text = "Barcha ziravorlar, shakar, sarimsoqpiyoz, kashnich, soya sousi, sirka, kunjut yog’i, kunjut va qovurilgan piyozni qo’shamiz.", images = emptyList()),
            RecipeDetail.Step(stepNum = 7, stepLabel = "Qadam 7", text = "Yaxshilab aralashtiramiz va salatni dasturxonga tortish mumkin. Yoqimli ishtaha!", images = emptyList()),
            RecipeDetail.Step(stepNum = 8, stepLabel = "Qadam 8", text = "Bu vaqt ichida barcha masalliqlar bir-biri bilan aralashadi va salatni dasturxonga tortish mumkin. Yoqimli ishtaha!", images = emptyList()),
        ),
        imageUrl = "https://zira.uz/wp-content/uploads/2022/07/ogurcy-po-koreyski-13.jpg",
        videoUrl = "",
        author = "Камола Мадгазиева",
        publishedDate = "22 Iyul, 2022",
        url = "https://zira.uz/uz/recipe/batafsil-fotoretsept-koreyscha-bodring/",
        createdAt = "2026-06-01 06:24:36",
        hasVideo = false
    )

    val recipeList = listOf(
        recipe,
        recipe2,
        recipe,
        recipe2,
        recipe
    )
}
