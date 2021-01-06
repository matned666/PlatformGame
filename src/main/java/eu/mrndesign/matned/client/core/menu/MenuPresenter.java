package eu.mrndesign.matned.client.core.menu;

public class MenuPresenter implements MenuContract.Presenter {

    private MenuContract.View view;

    public MenuPresenter(MenuContract.View view) {
        this.view = view;
    }

    @Override
    public void onButtonClick(MenuContract.MenuAction menuAction) {
        switch (menuAction){
            case START:{
                view.startGame();
            }
            case SETTINGS:{
                view.settings();
            }
        }
    }


}
