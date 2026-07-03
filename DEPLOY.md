# Deploying the browser version to Render

This project now has a **browser (HTML/GWT) build** in addition to the desktop
version. The libGDX game is compiled to JavaScript + WebGL, so it runs in a
browser and can be served from a live URL.

## How it works

- `html/` — the GWT module that compiles the game to JavaScript.
- `./gradlew html:stageDocs` — compiles the game and writes the finished static
  site into **`docs/`** (`index.html`, `html/` compiled JS, `assets/`).
- `render.yaml` — tells Render to serve `docs/` as a free Static Site.

`docs/` is committed to the repo, so Render never needs Java — it just serves
the pre-built files.

## One-time setup on Render

1. Push this repo to GitHub (it already points at
   `github.com/yashasvi0904/AngryBird-project-master`).
2. Go to <https://dashboard.render.com> → **New +** → **Static Site**.
3. Connect the GitHub repo `AngryBird-project-master`.
4. Render reads `render.yaml` automatically. If asked, set:
   - **Build Command:** `echo "prebuilt"`  (nothing to build)
   - **Publish Directory:** `docs`
5. Click **Create Static Site**. In ~1 minute you get a live URL like
   `https://angry-birds.onrender.com`.

## Updating the game later

Whenever you change the game code:

```bash
./gradlew html:stageDocs        # recompiles + refreshes docs/
git add docs && git commit -m "Update web build"
git push
```

Render auto-deploys the new `docs/` on push.

## Building the web version locally

```bash
# Requires JDK 17 or 21 (GWT does not support JDK 22+ for compiling).
./gradlew html:dist             # output: html/build/dist/
```

Test it locally with any static server, e.g.:

```bash
cd html/build/dist && python -m http.server 8080
# open http://localhost:8080
```

## Notes

- The desktop version is unchanged: `./gradlew lwjgl3:run`.
- Two small source tweaks were needed for GWT compatibility
  (`List.removeFirst()` → `List.remove(0)` in `Gameplay.java` / `Gameplay2.java`).
- The GWT build only compiles one browser permutation (`user.agent=safari`,
  which covers all modern Chromium/WebKit/Firefox browsers) to keep builds fast.
