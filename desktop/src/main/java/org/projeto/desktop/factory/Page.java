package org.projeto.desktop.factory;

import lombok.Getter;

@Getter
public record Page(String pageName, String fileName, String iconName) {
}
