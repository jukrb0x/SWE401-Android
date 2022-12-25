import { MedicationLog } from "../client";
import { Integer, Required, Property, Format } from "@tsed/schema";
import { MedicationModel } from "./MedicationModel";

export class MedicationLogModel implements MedicationLog {
  @Property(Number)
  @Integer()
  @Required()
  id: number;

  @Property(Date)
  @Format("date-time")
  @Required()
  updatedAt: Date;

  @Property(Date)
  @Format("date-time")
  @Required()
  createdAt: Date;

  @Property(Date)
  @Format("date-time")
  @Required()
  date: Date;

  @Property(Number)
  @Integer()
  @Required()
  status: number;

  @Property(() => MedicationModel)
  @Required()
  medication: MedicationModel;

  @Property(Number)
  @Integer()
  @Required()
  medicationId: number;
}

