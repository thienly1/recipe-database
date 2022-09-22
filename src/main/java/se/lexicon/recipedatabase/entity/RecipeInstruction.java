package se.lexicon.recipedatabase.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class RecipeInstruction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int instructionId;
    @Column(length = 1500)
    private String Instructions;

    public RecipeInstruction() {
    }

    public RecipeInstruction(String instructions) {
        Instructions = instructions;
    }

    public int getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(int instructionId) {
        this.instructionId = instructionId;
    }

    public String getInstructions() {
        return Instructions;
    }

    public void setInstructions(String instructions) {
        Instructions = instructions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeInstruction that = (RecipeInstruction) o;
        return getInstructionId() == that.getInstructionId() && Objects.equals(getInstructions(), that.getInstructions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInstructionId(), getInstructions());
    }

    @Override
    public String toString() {
        return "RecipeInstruction{" +
                "instructionId=" + instructionId +
                ", Instructions='" + Instructions + '\'' +
                '}';
    }
}
