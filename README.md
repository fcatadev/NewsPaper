[TR] 
# NewsPaper

Bu proje, Kotlin dilinde MVVM mimarisi kullanılarak geliştirilmiş bir haber uygulamasıdır. Giriş ve üyelik işlemleri için Firebase Authentication kullanılmış olup, şifremi unuttum, şifre sıfırlama ve beni hatırla gibi özellikler entegre edilmiştir. Uygulamanın ana sayfasında **En Önemli Haberler** ve **Teknoloji Haberleri** olmak üzere iki ayrı haber listesi bulunmaktadır. Haberler NewsAPI üzerinden alınmakta ve her bir habere tıklandığında detay sayfasına yönlendirme yapılmaktadır. Detay sayfasında kullanıcı, haberi favorilerine ekleyebilir ve daha sonra Favoriler sayfasında görüntüleyebilir. Favorilere eklenen haberler, istenildiğinde listeden kaldırılabilir.

![AppLogo](https://github.com/user-attachments/assets/95a5eb66-9fe7-4b0d-8b13-008c3755b75e)

## Kullanılan Teknolojiler ve Yapılar

- **MVVM** - Modern ve sürdürülebilir mimari yapısı
- **Hilt** - Dependency Injection
- **Firebase Auth** - Kullanıcı yönetimi ve kimlik doğrulama
- **Firebase Remote Config** - Uygulama içi dinamik konfigürasyonlar
- **Room DB** - Lokal veri yönetimi için SQLite tabanlı veritabanı
- **Shared Preferences** - Basit veri saklama yapısı
- **Retrofit** - API entegrasyonu için HTTP istemcisi
- **NewsAPI** - Haber verisi almak için kullanılan API
- **Glide** - Görsel yükleme ve önbellekleme kütüphanesi

## Ekstralar

- **Splash Ekranı:** Uygulama açılışında bir splash sayfası gösterilir ve burada Firebase Remote Config kullanılarak uygulamanın bakım ve sürüm kontrolü yapılır. 
  - **Bakım Kontrolü:** Eğer bakım modu aktifse kullanıcı bakım sayfasına yönlendirilir.
  - **Versiyon Kontrolü:** Cihazdaki uygulama sürümü, Firebase Remote Config üzerinden belirlenen minimum ve maksimum sürüm değerleriyle karşılaştırılır. Bu kontrole göre:
    - Zorunlu güncelleme yapılması gereken durumlarda, kullanıcıya zorunlu güncelleme pop-up'ı gösterilir.
    - İsteğe bağlı güncellemelerde ise kullanıcı, güncelleme yapmadan uygulamaya devam edebilir.
  - Her iki kontrol de geçilirse kullanıcı giriş sayfasına veya ana sayfaya yönlendirilir.

---

Bu proje, modern yazılım geliştirme standartlarını karşılayarak kullanıcı dostu bir deneyim sunmayı amaçlamaktadır. Katkılarınızı ve geri bildirimlerinizi bekliyorum!

---------------------------------------------------------------------------------------------

[EN] 
# NewsPaper

This project is a news application developed using Kotlin with the MVVM architecture. Firebase Authentication is used for login and registration processes, with features such as "Forgot Password", password reset, and "Remember Me" for quick login integrated into the system. On the main page, there are two news lists: **Top Headlines** and **Technology News**, which are fetched from the NewsAPI. Each news item can be clicked to navigate to the detail page, where users can add the news to their favorites. Previously added favorite news can be viewed on the Favorites page, and users can remove any news from their favorites if desired.

## Technologies and Structures Used

- **MVVM** - Modern and sustainable architectural pattern
- **Hilt** - Dependency Injection
- **Firebase Auth** - User management and authentication
- **Firebase Remote Config** - Dynamic configuration within the app
- **Room DB** - SQLite-based local database management
- **Shared Preferences** - Simple data storage structure
- **Retrofit** - HTTP client for API integration
- **NewsAPI** - API used to fetch news data
- **Glide** - Image loading and caching library

## Extras

- **Splash Screen:** A splash screen is displayed when the app is launched, utilizing Firebase Remote Config to manage maintenance and version control before accessing the app.
  - **Maintenance Control:** If maintenance mode is active, the user is redirected to the maintenance page.
  - **Version Control:** The app version on the device is compared with the minimum and maximum version values defined in Firebase Remote Config. Based on this check:
    - If a mandatory update is required, a mandatory update pop-up is shown to the user.
    - In case of an optional update, the user can continue using the app without updating.
  - If neither control is necessary, the user is directed to the login page or the main page.

---

This project aims to provide a user-friendly experience while meeting modern software development standards. I look forward to your contributions and feedback!
