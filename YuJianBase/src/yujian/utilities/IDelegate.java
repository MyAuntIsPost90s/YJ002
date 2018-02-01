package yujian.utilities;

public interface IDelegate<T> {
	T invoke(Object... args);
}
