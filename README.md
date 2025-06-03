# Android Compose App – JSONPlaceholder Viewer

## Gify prezentujące działanie aplikacji

<img src="https://github.com/user-attachments/assets/8701f3ae-b12c-4ebd-9c16-d5155b11e176" width="300"/>

<img src="https://github.com/user-attachments/assets/ba286ae6-cb2b-4640-8f1c-1f2f6d938354" width="300"/>



---

## Cel projektu

Aplikacja mobilna na Androida zbudowana przy użyciu Jetpack Compose. Umożliwia:

- przeglądanie listy postów z informacjami o autorach,
- przeglądanie szczegółów posta,
- przeglądanie profilu użytkownika i jego zadań.

---

## Wymagania funkcjonalne

### Lista funkcji z oznaczeniem działania

#### Ekran główny – lista postów
- ✅ Pobieranie danych z `/posts` i `/users`
- ✅ Wyświetlanie przewijalnej listy (`LazyColumn`)
- ✅ Każdy element zawiera tytuł posta i imię autora
- ✅ Kliknięcie w tytuł → ekran szczegółów posta
- ✅ Kliknięcie w autora → ekran szczegółów użytkownika

#### Ekran szczegółów posta
- ✅ Pobieranie danych z `/posts/{postId}`
- ✅ Wyświetlanie tytułu, treści i ID autora
- ✅ Nawigacja powrotna do ekranu głównego

#### Ekran szczegółów użytkownika
- ✅ Pobieranie danych z `/users/{userId}`
- ✅ Pobieranie zadań z `/todos?userId={userId}`
- 🟡 Wyświetlanie danych użytkownika (walnąłem się w akcji i wyświetla dane ale nie wszystkie)
- ✅ Lista zadań z informacją o ukończeniu
- ✅ Nawigacja powrotna

#### Obsługa ładowania i błędów
- ✅ Wyświetlanie wskaźnika ładowania (`CircularProgressIndicator`)
- ✅ Gdy nie ma internetu internet pokazuje się błąd

## Ograniczenia i rzeczy, które nie działają (jeszcze)
- 🟡 Nawigacja jest bez przycisków, nawiguje się gestami/przyciskami nawigacji, nie wiem czy może tak być, dla mnie jest git ale jak trzeba będzie zmienić to zmienie
- 🟡 Lista zadan nie przewija się wewnątrz tylko cały ekran
  
## Technologie

- Kotlin
- Jetpack Compose
- Architektura MVVM
- Retrofit + Gson
- Coroutines + Flow
- Jetpack Navigation
##
![image](https://github.com/user-attachments/assets/fc71fe9c-6bbd-409d-93f5-39d2276b110d)
