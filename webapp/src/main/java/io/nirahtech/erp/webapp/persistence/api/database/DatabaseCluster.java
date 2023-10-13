package io.nirahtech.erp.webapp.persistence.api.database;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class DatabaseCluster<T extends Database>  {

    private final T master;
    private final Set<T> nodes = new HashSet<>();
    private final DatabaseNodesSynchronizer databaseNodesSynchronizer;

    public DatabaseCluster(final T master, final T... nodes) {
        this.master = master;
        if (Objects.nonNull(nodes)) {
            this.nodes.addAll(Set.of(nodes));
        }
        this.databaseNodesSynchronizer = new DatabaseNodesSynchronizer(this.master, this.nodes);
    }

    public void synchronize() {
        this.databaseNodesSynchronizer.synchronize();
    }

    public final T master() {
        return this.master;
    }
    
    public final Collection<T> nodes() {
        return Collections.unmodifiableCollection(this.nodes);
    }

    private final class DatabaseNodesSynchronizer {
        
        private final T master;
        private final Collection<T> nodes = new HashSet<>();

        private DatabaseNodesSynchronizer(final T master, final Collection<T> nodes) {
            this.master = master;
            this.nodes.addAll(nodes);
        }
        
        private void synchronize() {
            File masterFile = this.master.getFile();
            this.nodes.stream().map(Database::getFile).forEach(nodeFile -> {
                try {
                    Files.copy(masterFile.toPath(), nodeFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            });
        }
    }
}
