# Android Compose App – JSONPlaceholder Viewer

## Gif prezentujący działanie aplikacji

<img src="https://github.com/user-attachments/assets/ac21acda-ffee-42e7-bc62-a07a9838890a" width="300"/>

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
- 🟡 Wyświetlanie danych użytkownika (walnąłem się w akcji i wyświetla dane ale nie wszystkie, ogarne potem :d)
- ✅ Lista zadań z informacją o ukończeniu
- ✅ Nawigacja powrotna

#### Obsługa ładowania i błędów
- ✅ Wyświetlanie wskaźnika ładowania (`CircularProgressIndicator`)

## Ograniczenia i rzeczy, które nie działają (jeszcze)
- 🟡 Obsługa błędów z komunikatem (no coś tam jest, ale nie działa bo jak nie ma internetu to ładuje w nieskonczoność, też ogarne potem bo nie starczyło czasu :v)
- 🟡 Nawigacja jest bez przycisków, nawiguje się gestami/przyciskami nawigacji, nie wiem czy może tak być, dla mnie jest git ale jak trzeba będzie zmienić to zmienie
- 🟡 Lista zadan nie przewija się wewnątrz tylko cały ekran (ogarnie się jeszcze na spokojnie)
  
## Technologie

- Kotlin
- Jetpack Compose
- Architektura MVVM
- Retrofit + Gson
- Coroutines + Flow
- Jetpack Navigation
##
![image](https://github.com/user-attachments/assets/fc71fe9c-6bbd-409d-93f5-39d2276b110d)
