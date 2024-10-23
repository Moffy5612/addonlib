package com.moffy5612.addonlib.api;

import java.util.HashSet;

public final class ContentHandlerProvider {
    public static final ContentHandlerProvider INSTANCE = new ContentHandlerProvider();

    private HashSet<ContentHandlerBase> handlers;

    private ContentHandlerProvider(){
        handlers = new HashSet<>();
    }

    public void addContentHandler(ContentHandlerBase contentHandler){
        this.handlers.add(contentHandler);
    }

    public HashSet<ContentHandlerBase> getHandlers(){
        return this.handlers;
    }
}
