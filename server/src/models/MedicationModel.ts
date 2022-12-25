import { Medication } from "../client";
import { Integer, Required, Property, Format, CollectionOf } from "@tsed/schema";
import { UserModel } from "./UserModel";
import { MedicationLogModel } from "./MedicationLogModel";

export class MedicationModel implements Medication {
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

  @Property(String)
  @Required()
  name: string;

  @Property(String)
  @Required()
  description: string;

  @Property(Number)
  @Integer()
  @Required()
  status: number;

  @Property(() => UserModel)
  @Required()
  user: UserModel;

  @Property(Number)
  @Integer()
  @Required()
  userId: number;

  @CollectionOf(() => MedicationLogModel)
  @Required()
  MedicationLog: MedicationLogModel[];
}

