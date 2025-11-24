OV N-Land Network - Android Project

Inhalt:
- Minimal Android Studio project (app module)
- MainActivity.java using WebView to load your URL and keep internal links inside the app
- network_security_config.xml to disallow cleartext for your domain

Pfad: app/src/main/java/com/dielinke/ovnland/network/MainActivity.java

Build via GitHub Actions (workflow included).

Schritte um CI-Build zu nutzen:
1) Erstelle ein GitHub-Repo und pushe dieses Projekt.
2) Aktiviere GitHub Actions.
3) Workflow wird bei push auf 'main' oder manuell ausgelöst.
4) Nach erfolgreichem Lauf findest du das APK als Artefakt.

Hinweis: Für Play Store Release signiere die APK mit einem Keystore.
