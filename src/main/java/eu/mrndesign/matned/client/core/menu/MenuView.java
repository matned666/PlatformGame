package eu.mrndesign.matned.client.core.menu;

public class MenuView implements MenuContract.View {

    private MenuContract.Presenter presenter;
    private MenuScreenInterface.ScreenListener screenListener;

    public MenuView(MenuScreenInterface.ScreenListener screenListener) {
        this.screenListener = screenListener;
        this.presenter = new MenuPresenter(this);
    }

    @Override
    public void onStartGame() {
        presenter.onButtonClick(MenuContract.MenuAction.START);
    }

    @Override
    public void onSettings() {

    }

    @Override
    public void startGame() {
        screenListener.onStartGame();
    }

    @Override
    public void settings() {

    }

}
