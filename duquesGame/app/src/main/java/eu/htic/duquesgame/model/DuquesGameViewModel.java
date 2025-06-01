package eu.htic.duquesgame.model;

import androidx.lifecycle.ViewModel;

public class DuquesGameViewModel extends ViewModel {

    private DuquesGame duquesGame;

    public DuquesGameViewModel() {
        duquesGame = new DuquesGame();
    }

    public void startNewGame() {
        duquesGame = new DuquesGame();
    }
    public DuquesGame getGame() {
        return duquesGame;
    }
}
