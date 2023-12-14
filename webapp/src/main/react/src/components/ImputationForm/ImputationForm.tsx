import React, { useState } from 'react';
import './ImputationForm.css';
import { LocalDateTime, TimeUnit } from '@nirahtech/datetime';
import { Company, DateTimeHelper, Imputation, Project } from '@nirahtech/erp';

interface Properties {
  company: Company;
  onNewImputation: Function;
}

function ImputationForm({company, onNewImputation}: Properties) {
  const [date, setDate] = useState<LocalDateTime>();
  const [duration, setDuration] = useState<number>();
  const [timeUnit] = useState<TimeUnit>(TimeUnit.MINUTES);
  const [project, setProject] = useState<Project>();
  const [details, setDetails] = useState<string>("");

  const now: LocalDateTime = LocalDateTime.now();



  const onSubmitHandler = (event: any) => {
    event.preventDefault();
    if (date && duration && timeUnit && project) {
      let imputation: Imputation = Imputation.of(date, duration, timeUnit, project, details);
      if (onNewImputation) {
        onNewImputation(imputation);
      }
    }
  }

  const updateFromDate = (event: any) => {
    setDate(LocalDateTime.from(new Date(event.target.value)));
  }

  const updateDuration = (event: any) => {
    let to: LocalDateTime = LocalDateTime.from(new Date(event.target.value));
    if (date) {
      let minutes: number = DateTimeHelper.differenceInMinutes(date, to);
      setDuration(minutes);
    }
  }

  const updateProject = (event: any) => {
    console.log(event.target.value);
  }

  return (
    <div className='ImputationForm-Component'>
        <form onSubmit={onSubmitHandler} >
            <div>
              <label htmlFor="">De *</label>
              <input type="datetime-local" required onChange={updateFromDate}  />
            </div>
            <div>
              <label htmlFor="">Jusqu'à *</label>
              <input type="datetime-local"  required onChange={updateDuration} />
            </div>
            <div>
              <label htmlFor="">Projet *</label>
              <select name="" id="" required onChange={updateProject}>
                {Array.from(company.getProjectsRegistry().getProjects()).map((project) => (
                  <option value={project.name}>{project.name}</option>
                ))}
              </select>
            </div>
            <div>
              <label htmlFor="">Description</label>
              <textarea name="" id="" ></textarea>
            </div>
            <div>
              <button type="submit">Imputer</button>
            </div>
        </form>
    </div>
  );
}

export default ImputationForm;
