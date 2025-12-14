package org.govnorganization.taskmweb.taskmweb.repositories;

import org.govnorganization.taskmweb.taskmweb.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskModelRepository extends JpaRepository<TaskModel, Long> {}
