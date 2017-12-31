package eus.julenugalde.thinkinginjava.threading;

import java.awt.Container;

public class SuspendResume extends Blockable {
	SuspendResume(Container c) {
		super(c);
		new Resumer(this);
	}
}
