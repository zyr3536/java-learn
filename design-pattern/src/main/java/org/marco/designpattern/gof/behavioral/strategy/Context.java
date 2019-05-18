package org.marco.designpattern.gof.behavioral.strategy;

public class Context {
    /**
     * 持有一个策略
     */
    private IStrategy strategy;

    public IStrategy getStrategy() {
        return strategy;
    }

    /**
     * 设置策略
     * @param strategy
     */
    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void algorithm() {
        // 具体行为委托给策略类去做
        strategy.algorithm();
    }
}
