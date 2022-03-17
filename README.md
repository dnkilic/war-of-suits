## War of Suits

I choose a modular gradle setup rather than a monolithic one. So that five developers may work on different features without depending on each other. One another advantage would be sharing the commons between app/wearable/auto projects, so it'll decrease the code duplication.

Regarding architecture I choose MVVM. MVVM architecture is the official recommendation (although it might change with introduction of compose)

## Technologies
Compose, Hilt, Kotlin Flow, Mockk, Compose Navigation.

This app is compose only which means there is no Fragment and there is only one activity. I especially enjoyed with Compose Animations during the implementation.

# Screens
Users can start a game after inputting their name
<p align="middle">
  <img src="/screenshots/player_screen.png" width="150" />   
  <img src="/screenshots/game_screen1.png" width="150" /> 
  <img src="/screenshots/game_screen2.png" width="150" />
  <img src="/screenshots/game_screen3.png" width="150" />
</p>
<p align="middle">
    <img src="/screenshots/game_screen4.png" width="150" />   
    <img src="/screenshots/game_screen5.png" width="150" /> 
    <img src="/screenshots/game_screen7.png" width="150" /> 
    <img src="/screenshots/game_screen8.png" width="150" /> 
</p>