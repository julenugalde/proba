package eus.julenugalde.thinkinginjava.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.swing.JButton;
import java.util.Map;

public class AlphabeticPolicy extends FocusTraversalPolicy {
	private SortedMap<String, Component> getSortedButtons(Container focusCycleRoot) {
		if (focusCycleRoot == null) {
			throw new IllegalArgumentException("focusCycleRoot can't be null");
		}
		// Will sort all buttons by text.
		SortedMap<String, Component> result = new TreeMap<String, Component>(); 
		sortRecursive(result, focusCycleRoot);
		printMapKeys(result);
		return result;
	}

	private void printMapKeys(SortedMap<String, Component> result) {
		java.util.Set<String> keys = result.keySet();
		for (String key : keys) {
			System.out.println(key);
		}
	}

	private void sortRecursive(Map<String, Component> buttons, Container container) {
		for (int i = 0; i < container.getComponentCount(); i++) {
			Component c = container.getComponent(i);
			if (c instanceof JButton) { // Found another button to sort.
				buttons.put(((JButton) c).getText(), c);
			}
			if (c instanceof Container) { // Found a container to search.
				sortRecursive(buttons, (Container) c);
			}
		}
	}

	// The rest of the code implements the FocusTraversalPolicy interface.

	public Component getFirstComponent(Container focusCycleRoot) {
		SortedMap<String,Component> buttons = getSortedButtons(focusCycleRoot);
		if (buttons.isEmpty()) {
			return null;
		}
		return (Component) buttons.get(buttons.firstKey());
	}

	public Component getLastComponent(Container focusCycleRoot) {
		SortedMap<String,Component> buttons = getSortedButtons(focusCycleRoot);
		if (buttons.isEmpty()) {
			return null;
		}
		return (Component) buttons.get(buttons.lastKey());
	}

	public Component getDefaultComponent(Container focusCycleRoot) {
		return getFirstComponent(focusCycleRoot);
	}

	public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
		if (!(aComponent instanceof JButton)) {
			return null;
		}
		SortedMap<String,Component> buttons = getSortedButtons(focusCycleRoot);
		// Find all buttons after the current one.
		String nextName = ((JButton) aComponent).getText() + "\0";
		SortedMap<String,Component> nextButtons = buttons.tailMap(nextName);
		if (nextButtons.isEmpty()) { // Wrapped back to beginning
			if (!buttons.isEmpty()) {
				return (Component) buttons.get(buttons.firstKey());
			}
			return null; // Degenerate case of no buttons.
		}
		return (Component) nextButtons.get(nextButtons.firstKey());
	}

	public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
		if (!(aComponent instanceof JButton)) {
			return null;
		}

		SortedMap<String,Component> buttons = getSortedButtons(focusCycleRoot);
		SortedMap<String,Component> prevButtons = // Find all buttons before this one.
				buttons.headMap(((JButton) aComponent).getText());
		if (prevButtons.isEmpty()) { // Wrapped back to end.
			if (!buttons.isEmpty()) {
				return (Component) buttons.get(buttons.lastKey());
			}
			return null; // Degenerate case of no buttons.
		}
		return (Component) prevButtons.get(prevButtons.lastKey());
	}

}
