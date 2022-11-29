package com.julianmartinez.sportscentrerest.service;

import com.julianmartinez.sportscentrerest.model.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;

@ExtendWith(MockitoExtension.class)
class ResourceServiceTest {

    @InjectMocks
    private ResourceService resourceService;

    @Test
    void shouldRetrieveAllResources() {
        final List<Resource> expectedResult = List.of();
        final List<Resource> actualResult = this.resourceService.retrieveAllResources();
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void shouldRetrieveResource() {
        final int id = 1;
        final Resource expectedResult = Resource.builder().id(id).build();
        final Resource actualResult = this.resourceService.retrieveResource(id);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void shouldAddResource() {
        final String name = "Tennis 1";
        final Resource resource = Resource.builder().name(name).build();
        final Resource actualResult = this.resourceService.addResource(resource);
        assertThat(actualResult).returns(name, from(Resource::getName));
    }

    @Test
    void shouldModifyResource() {
        final int id = 1;
        final String name = "Tennis 1";
        final Resource resource = Resource.builder().name(name).build();
        final Resource actualResult = this.resourceService.modifyResource(id, resource);
        assertThat(actualResult)
                .returns(id, from(Resource::getId))
                .returns(resource.getName(), from(Resource::getName));
    }

    @Test
    void shouldDeleteResource() {
        final int id = 1;
        assertThat(this.resourceService.removeResource(id)).isTrue();
    }

}