package io.fabasoad.pojo.reflections;

import static java.util.stream.Collectors.toSet;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import io.fabasoad.pojo.reflections.exceptions.ReflectionsException;
import java.io.IOException;
import java.util.Set;

public class Reflections {

  @SuppressWarnings("All")
  public Set<Class<?>> getClasses(final String packageName) {
    final ClassPath classPath;
    try {
      classPath = ClassPath.from(ClassLoader.getSystemClassLoader());
    } catch (IOException e) {
      throw new ReflectionsException("Failed to read class path", e);
    }
    return classPath
        .getAllClasses()
        .stream()
        .filter(c -> c.getPackageName().equalsIgnoreCase(packageName))
        .map(ClassInfo::load)
        .collect(toSet());
  }
}
