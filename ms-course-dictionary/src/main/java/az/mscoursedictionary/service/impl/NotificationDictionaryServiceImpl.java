package az.mscoursedictionary.service.impl;

import az.mscoursedictionary.dao.NotificationRequest;
import az.mscoursedictionary.dao.NotificationResponse;
import az.mscoursedictionary.entity.EnrollmentEntity;
import az.mscoursedictionary.entity.NotificationEntity;
import az.mscoursedictionary.exception.NotFoundException;
import az.mscoursedictionary.repository.EnrollmentRepository;
import az.mscoursedictionary.repository.NotificationRepository;
import az.mscoursedictionary.service.NotificationDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.mscoursedictionary.enums.ErrorMessages.NOTIFICATION_NOT_FOUND;
import static az.mscoursedictionary.mapper.NotificationMapper.NOTIFICATION_MAPPER;
import static java.lang.String.format;

@Service
public class NotificationDictionaryServiceImpl implements NotificationDictionaryService {
    private final NotificationRepository notificationRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public NotificationDictionaryServiceImpl(NotificationRepository notificationRepository, EnrollmentRepository enrollmentRepository){
        this.notificationRepository = notificationRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public NotificationResponse sendCustomNotification(NotificationRequest notificationRequest){
        List<EnrollmentEntity> enrollments = enrollmentRepository.findAllByUsernameIsIn(notificationRequest.getReceivers());
        assert !enrollments.isEmpty();
        NotificationEntity notificationEntity = NOTIFICATION_MAPPER.mapToEntity(notificationRequest,enrollments);
        notificationRepository.save(notificationEntity);
        return NOTIFICATION_MAPPER.mapToResponse(notificationEntity);

    }

    @Override
    public List<NotificationResponse> getAllNotifications(){
        return notificationRepository.findAll().stream().map(NOTIFICATION_MAPPER::mapToResponse).toList();
    }

    @Override
    public NotificationResponse getNotificationById(Long notificationId){
        return notificationRepository.findById(notificationId).map(NOTIFICATION_MAPPER::mapToResponse).
                orElseThrow(()-> new NotFoundException(
                        format(
                            NOTIFICATION_NOT_FOUND.getMessage(),
                            notificationId
                        )
                ));
    }


}
