package it.unical.inf.ea.uniprjms.ms.course.config;

import it.unical.inf.ea.uniprjms.ms.course.data.entities.Teacher;
import it.unical.inf.ea.uniprjms.ms.course.dto.TeacherBasicDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper getModelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

    modelMapper.createTypeMap(Teacher.class, TeacherBasicDto.class).addMappings(new PropertyMap<Teacher, TeacherBasicDto>() {
      @Override
      protected void configure() {
        // define a converter that takes the whole "person"
        using(ctx -> generateFullname(((Teacher) ctx.getSource()).getFirstName(), ((Teacher) ctx.getSource()).getLastName()))
            // Map the compliete source here
            .map(source, destination.getFullName());
      }
    });

    return modelMapper;
  }

  private String generateFullname(String firstname, String lastname) {
    return firstname + " " + lastname;
  }
}