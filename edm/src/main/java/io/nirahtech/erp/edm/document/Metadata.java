package io.nirahtech.erp.edm.document;

import java.io.Serializable;

/**
 * Metadata
 */
public record Metadata(String name, Object value) implements Serializable {
}