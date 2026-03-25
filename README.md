# Library Management System (Java 17)

This project is a console-based Library Management System (LMS) built with plain Java and focused on clean, educational use of design patterns.

## Patterns Used

- Singleton: `LibraryService` is a single shared service instance.
- Factory: `DefaultBookFactory` creates `RegularBook`, `EBook`, and `PremiumBook` (decorated policy).
- Decorator: `PremiumBookDecorator` adds 10 borrowing days without changing `Book`.
- Proxy: `PremiumEBookProxy` restricts full eBook access to premium users.
- Chain of Responsibility: `Librarian -> Manager -> Director` approve borrow requests by requested days.
- Facade: `LibraryFacade` exposes a simple `borrowBook(user, title, days)` workflow.
- Adapter: `JsonBookAdapter` converts external JSON schema into internal `Book`.
- Observer: `BookWaitlist` notifies subscribed users when a book becomes available.

## Package Structure

- `app`
- `model`
- `service`
- `factory`
- `decorator`
- `proxy`
- `adapter`
- `observer`
- `chain`
- `facade`

## How To Run

1. Compile:

```powershell
$files = Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -d out $files
```

2. Run:

```powershell
java -cp out app.Main
```

## Requirement Mapping

- Requirement 1 -> Singleton
- Requirement 2 -> Factory
- Requirement 3 -> Decorator
- Requirement 4 -> Proxy
- Requirement 5 -> Chain of Responsibility
- Requirement 6 -> Facade
- Requirement 7 -> Adapter
- Requirement 8 -> Observer
- Requirement 9 -> Extended Chain of Responsibility
