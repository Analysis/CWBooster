package scripts.red_CWBooster.Tasks;

public interface Task {
    public abstract void execute();

    public abstract boolean validate();

    public abstract String status();
}
