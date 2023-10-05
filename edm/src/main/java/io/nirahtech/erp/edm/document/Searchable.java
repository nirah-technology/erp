package io.nirahtech.erp.edm.document;

public interface Searchable {
    boolean search(String data);
    boolean searchInfileOnly(String data);
    boolean searchInMetadatasOnly(String data);
    boolean searchInTagsOnly(String data);
    boolean searchInCommentsOnly(String data);
}
