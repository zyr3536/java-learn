package org.marco.designpattern.gof.creational.builder.simple;

public class Family {
    private String mother;
    private String father;
    private String child;

    @Override
    public String toString() {
        return "Family{" +
                "mother='" + mother + '\'' +
                ", father='" + father + '\'' +
                ", child='" + child + '\'' +
                '}';
    }

    private Family(Builder builder) {
        this.mother = builder.mother;
        this.father = builder.father;
        this.child = builder.child;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String mother;
        private String father;
        private String child;

        public Builder mother(String mother) {
            this.mother = mother;
            return this;
        }

        public Builder father(String father) {
            this.father = father;
            return this;
        }
        public Builder child(String child) {
            this.child = child;
            return this;
        }

        public Family build() {
            return new Family(this);
        }

    }
}
