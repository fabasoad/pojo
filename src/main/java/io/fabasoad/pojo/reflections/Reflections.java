package io.fabasoad.pojo.reflections;

import static java.util.stream.Collectors.toSet;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import io.fabasoad.pojo.reflections.exceptions.ReflectionsException;
import java.io.IOException;
import java.util.Set;
import java.util.function.Predicate;

public class Reflections {

  @SuppressWarnings("All")
  public Set<Class<?>> getClasses(final String packageName, final boolean recursive) {
    final ClassPath classPath;
    try {
      classPath = ClassPath.from(ClassLoader.getSystemClassLoader());
    } catch (IOException e) {
      throw new ReflectionsException("Failed to read class path", e);
    }
    final Predicate<ClassInfo> filter =
        recursive
            ? c -> c.getPackageName().startsWith(packageName)
            : c -> c.getPackageName().equalsIgnoreCase(packageName);
    return classPath.getAllClasses().stream().filter(filter).map(ClassInfo::load).collect(toSet());
  }
}
