lazy val `import-counter` =
  project
    .in(file("."))
    .withEffectMonad
    .withFileIO
    .withTesting