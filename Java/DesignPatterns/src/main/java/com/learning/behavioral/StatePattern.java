package com.learning.behavioral;

/**
 * 对有状态的对象，把复杂的“判断逻辑”提取到不同的状态对象中，允许状态对象在其内部状态发生改变时改变其行为。
 * 状态是从内部发生改变的。
 */
public class StatePattern {
    public static void main(String[] args) {
        Context context = new Context(new ConcreteStateA());
        context.handle();
        context.handle();
        context.handle();
        context.handle();
        context.handle();
    }
}

class Context {
    private State state;

    public Context(State state) {
        this.state = state;
    }

    public void handle() {
        state.handle(this);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

abstract class State {
    abstract void handle(Context context);
}

class ConcreteStateA extends State {
    @Override
    void handle(Context context) {
        System.out.println("Current state is A, next to B");
        context.setState(new ConcreteStateB());
    }
}

class ConcreteStateB extends State {
    @Override
    void handle(Context context) {
        System.out.println("Current state is B, next to A");
        context.setState(new ConcreteStateA());
    }
}
