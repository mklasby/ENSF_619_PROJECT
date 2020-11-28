package View.ViewControllers;

import CommonMessage.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.*;
import javax.swing.*;

import org.json.JSONException;

import java.awt.event.*;
import View.Views.*;

/**
 * Base class for SubView Controllers
 * 
 * @author Mike Lasby
 * @since Nov. 25, 2020
 * @version 1.1
 */

public abstract class ViewController implements MessageConstants {
    public GuiController guiController;
    public SubView view;
    public ActionListener menuListener;

    ViewController() {
    }

    ViewController(GuiController guiController) {
        this.guiController = guiController;
    }

    ViewController(SubView view, GuiController guiController) {
        this.guiController = guiController;
        this.view = view;
    }

    protected void registerGuiMenuButton(ActionListener listener) {
        view.registerGuiMenuButton(listener);
    }

    protected boolean areFieldsEmpty() {
        HashMap<String, JTextField> fields = view.getFields();
        for (String key : fields.keySet()) {
            if (fields.get(key).getText().equals("") | fields.get(key).getText().equals(" ")) {
                return true;
            }
        }
        return false;
    }

    protected void clearFields() {
        HashMap<String, JTextField> fields = view.getFields();
        for (String key : fields.keySet()) {
            fields.get(key).setText("");
        }
    }

    protected boolean isErrorMessage(Message response) {
        try {
            if (response.get(STATUS).equals(ERROR)) {
                view.flashErrorMessage((String) response.get(DATA));
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected abstract HashMap<String, JTextField> getInfoFields();

    protected abstract boolean areInfoFieldsEmpty();

    protected abstract void clearInfoFields();
}
