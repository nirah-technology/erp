from abc import abstractmethod
from shutil import copy
from os import listdir, removedirs

TOMCAT_WEBAPP_FOLDER: str = "/opt/apache-tomcat-10.1.14/webapps/"
TARGET_FOLDER: str = "target/"

class Installer:

    @abstractmethod
    def install(self, file_to_install: str):
        raise NotImplementedError()


class Uninstaller:

    @abstractmethod
    def uninstall(self, file_to_uninstall: str):
        raise NotImplementedError()

class WarInstaller(Installer):
    EXTENSION: str = ".war"

    def __init__(self, install_directory: str) -> None:
        self.__install_directory: str = install_directory
    
    def install(self, file_to_install: str):
        if (file_to_install.endswith(WarInstaller.EXTENSION)):
            copy(file_to_install, self.__install_directory)

class WarUninstaller(Uninstaller):
    EXTENSION: str = ".war"

    def __init__(self, install_directory: str) -> None:
        self.__install_directory: str = install_directory
    
    def uninstall(self, file_to_uninstall: str):
        if (file_to_uninstall.endswith(WarInstaller.EXTENSION)):
            copy(file_to_uninstall, self.__install_directory)

def list_war_files(folder: str) -> list[str]:
    return [str(folder+file) for file in listdir(folder) if str(file).endswith(WarInstaller.EXTENSION)]

def main():
    war_files: list[str] = list_war_files(TARGET_FOLDER)
    installer: Installer = WarInstaller(install_directory=TOMCAT_WEBAPP_FOLDER)
    for war_file in war_files:
        installer.install(file_to_install=war_file)

if (__name__ == "__main__"):
    main()