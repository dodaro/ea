package it.unical.inf.ea.uniprj.data.service;

import org.springframework.stereotype.Service;

import it.unical.inf.ea.uniprj.data.dao.LessonDao;
import it.unical.inf.ea.uniprj.data.entities.Lesson;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonDao lessonDao;

    @Override
    public Lesson save(Lesson lesson) {
        return lessonDao.save(lesson);
    }


    
}
