package memorygame;

import javax.swing.*;

class CardWorker extends SwingWorker {
    private Card button;

    CardWorker(Card button) {
        this.button = button;
    }

    @Override
    protected Object doInBackground() throws Exception {
        Thread.sleep(1000);
        if (button.getState() != CardButtonState.NOTACTIVE)
            button.setIcon(null);   // Remove icon
        return null;
    }
}
