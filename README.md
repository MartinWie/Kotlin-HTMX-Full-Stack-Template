# Kotlin HTMX Tailwind FullStack Template
A template for creating full-stack services with HTMX, Kotlin, Ktor and Tailwind (THKK Stack).
This template is built to be a quick-start for most web-based applications, with a focus on good DX for Kotlin developers. For production use, please check out: [Production-Optimizations](#Production-Optimizations)

1) Clone the project
2) Remove the Ktor plugins you don't need 
3) Start implementing your project

### Live-Development

Run the following command/script:

```bash
bash buildAndReloadBrowserOnsave.sh
```

This script leverages `entr` to trigger another script on every `*.kt` file change. Since `entr` will exit if new files are created, this script is looped for continuous monitoring (for more details, read the `entr` man page). 
The secondary script (`startServer.sh`) builds the tailwind CSS and runs the app server using Gradle. 
Once a specific log stage is reached, it either starts or refreshes a browser session with `browser-sync` to live update the page. 
If the `startServer` script receives a user exit command, we run a stop script and break out of the loop to exit `buildAndReloadBrowserOnsave.sh`.

### Install-Tooling

**Tailwind**: For CSS generation.
```bash
npm install -D tailwindcss
```

**Browser-sync**: Auto-refreshes browser on changes. For details, check out `startServer.sh`.
```bash
npm install -g browser-sync
```

**Entr**: Triggers action on file changes. For details, check out `startServer.sh`.
```bash
brew install entr
```

## Production-Optimizations

- Migrate to HTML templates instead of fully utilizing the Kotlin DSL to save CPU resources when constructing the page each time.

## Sample usages

- [Simple todo-list](https://github.com/MartinWie/Kotlin-HTMX-Todolist)


## Helper website to convert form HTML to Kotlin HTML DSL

[HTML to Kotlin HTML DSL Converter](https://github.com/MartinWie/Kotlin-HTMX-Full-Stack-Template/blob/main/HTML-to-Kotlin.html)


## Support me :heart: :star: :money_with_wings:
If this project provided value, and you want to give something back, you can give the repo a star or support me, by tipping me a coffee.

<a href="https://buymeacoffee.com/MartinWie" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/v2/default-blue.png" alt="Buy Me A Coffee" width="170"></a>
