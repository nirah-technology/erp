package io.nirahtech.erp.webapp.persistence.api.database;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SqlBuilder {

    private SqlBuilder() { }

    public static final SqlSelectBuilder select(final String... properties) {
        return new SqlSelectBuilder(Stream.of(properties).collect(Collectors.joining(", ")));
    }
    public static final SqlBuilder insert(Map<String, Object> properties) {
        
    }

    public static final SqlBuilder update() {

    }

    public static final SqlBuilder createTable(Class<?> table) {

    }

    public static SqlBuilder delete(Class<?> table) {

    }

    public static final class SqlSelectBuilder {

        public final StringBuilder builder = new StringBuilder();

        private SqlSelectBuilder(final String properties) {
            this.builder.append("SELECT")
                .append(" ")
                .append(properties)
                .append(" ");
        }

        public final SqlPotentialFilteredSelectBuilder from(final Class<?> table) {
            this.builder.append("FROM ")
                .append(" ")
                .append(table.getSimpleName())
                .append(" ");
            return new SqlPotentialFilteredSelectBuilder(this.builder);
        }

        @Override
        public String toString() {
            return this.builder.toString();
        }
    }


    public static final class SqlPotentialFilteredSelectBuilder {

        private final StringBuilder builder;

        public SqlPotentialFilteredSelectBuilder(final StringBuilder builder) {
            this.builder = builder;
        }

        public final SqlFilteredSelectBuilder where(final String propertyCondition) {
            this.builder
                .append("WHERE")
                .append(" ")
                .append(propertyCondition)
                .append(" ");
            return new SqlFilteredSelectBuilder(this.builder);
        }
        
        public final SqlAdvancedSelectBuilder limit(final int limit) {
            this.builder
                .append("LIMIT")
                .append(" ")
                .append(limit)
                .append(" ");
            return new SqlAdvancedSelectBuilder(this.builder);
        }
        public final SqlAdvancedSelectBuilder groupBy(final String... properties) {
            this.builder
                .append("GROUP BY")
                .append(" ")
                .append(Stream.of(properties).collect(Collectors.joining(", ")))
                .append(" ");
                return new SqlAdvancedSelectBuilder(this.builder);
        }
        public final SqlAdvancedSelectBuilder orderBy(final String... properties) {
            this.builder
                .append("ORDER BY")
                .append(" ")
                .append(Stream.of(properties).collect(Collectors.joining(", ")))
                .append(" ");
                return new SqlAdvancedSelectBuilder(this.builder);
        }
        @Override
        public String toString() {
            return this.builder.toString();
        }
    }

    

    public static final class SqlFilteredSelectBuilder {

        private final StringBuilder builder;

        public SqlFilteredSelectBuilder(final StringBuilder builder) {
            this.builder = builder;
        }

        public final SqlFilteredSelectBuilder and(final String propertyCondition) {
            this.builder
                .append("AND")
                .append(" ")
                .append(propertyCondition)
                .append(" ");
            return this;
        }
        public final SqlFilteredSelectBuilder or(final String propertyCondition) {
            this.builder
                .append("OR")
                .append(" ")
                .append(propertyCondition)
                .append(" ");
            return this;
        }
        public final SqlAdvancedSelectBuilder limit(final int limit) {
            this.builder
                .append("LIMIT")
                .append(" ")
                .append(limit)
                .append(" ");
                return new SqlAdvancedSelectBuilder(this.builder);
        }
        public final SqlAdvancedSelectBuilder groupBy(final String... properties) {
            this.builder
                .append("GROUP BY")
                .append(" ")
                .append(Stream.of(properties).collect(Collectors.joining(", ")))
                .append(" ");
                return new SqlAdvancedSelectBuilder(this.builder);
        }
        public final SqlAdvancedSelectBuilder orderBy(final String... properties) {
            this.builder
                .append("ORDER BY")
                .append(" ")
                .append(Stream.of(properties).collect(Collectors.joining(", ")))
                .append(" ");
                return new SqlAdvancedSelectBuilder(this.builder);
        }
        @Override
        public String toString() {
            return this.builder.toString();
        }
    }

    

    public static final class SqlAdvancedSelectBuilder {

        private final StringBuilder builder;

        public SqlAdvancedSelectBuilder(final StringBuilder builder) {
            this.builder = builder;
        }

        public final SqlAdvancedSelectBuilder limit(final int limit) {
            this.builder
                .append("LIMIT")
                .append(" ")
                .append(limit)
                .append(" ");
            return this;   
        }
        public final SqlAdvancedSelectBuilder groupBy(final String... properties) {
            this.builder
                .append("GROUP BY")
                .append(" ")
                .append(Stream.of(properties).collect(Collectors.joining(", ")))
                .append(" ");
            return this;   
        }
        public final SqlAdvancedSelectBuilder orderBy(final String... properties) {
            this.builder
                .append("ORDER BY")
                .append(" ")
                .append(Stream.of(properties).collect(Collectors.joining(", ")))
                .append(" ");
            return this;   
        }
        @Override
        public String toString() {
            return this.builder.toString();
        }
    }

    
}
