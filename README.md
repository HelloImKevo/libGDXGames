# libGDXGames
Repository for a collection of Android games built with the libGDX framework.

# libGDX Project Generation
- Run the GDX-Setup JAR.
- Input project name, ex: "JackTheGiantGame".
- Input package, ex: `com.kevo.jackgiant`.
- Select project destination, ex: `/Users/kevo/GitProjects/libGDXGames/JackTheGiantGame`.
- Select Android SDK location, ex: `/Users/kevo/android-sdks`.
- De-select iOS and HTML.
- Select the "Freetype" and "Box2d" extensions.
- Under "Advanced", check "Use Kotlin".
- Click "Generate".

# libGDX Initial Project Configuration
- Open the new project with Android Studio.
- Open the Run Configurations, and click "Edit Configurations...".
- Click the "+" to add a new configuration.
- Select the "Application" type, and name it "Desktop".
- For "Main Class", select the `DesktopLauncher`, ex: `com.kevo.jackgiant.desktop.DesktopLauncher`.
- For "Working Directory", select the `android/assets` folder, ex: `JackTheGiantGame/android/assets`.
- For "Use classpath of module", select the `desktop` module.
- Run the new "Desktop" run configuration. You should see the "Bad Logic" logo in a game window. Celebrate success. \o/

# Useful References
https://github.com/libgdx/libgdx  
https://libgdx.com/dev/project-generation  

