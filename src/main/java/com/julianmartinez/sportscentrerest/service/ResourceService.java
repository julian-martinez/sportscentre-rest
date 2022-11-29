package com.julianmartinez.sportscentrerest.service;

import com.julianmartinez.sportscentrerest.model.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class ResourceService {
    public List<Resource> retrieveAllResources() {
        return List.of();
    }

    public Resource retrieveResource(final int id) {
        return Resource.builder()
                .id(id)
                .build();
    }

    public Resource addResource(final Resource resource) {
        return Resource.builder()
                .id(ThreadLocalRandom.current().nextInt())
                .name(resource.getName())
                .build();
    }

    public Resource modifyResource(final int id, final Resource resource) {
        return Resource.builder()
                .id(id)
                .name(resource.getName())
                .build();
    }

    public boolean removeResource(final int id) {
        return true;
    }
}
