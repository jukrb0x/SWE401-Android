import { User } from "../client";
import { Integer, Required, Property, Format, CollectionOf } from "@tsed/schema";
import { ActivityModel } from "./ActivityModel";
import { SleepModel } from "./SleepModel";
import { MedicationModel } from "./MedicationModel";

export class UserModel implements User {
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
  email: string;

  @Property(String)
  @Required()
  firstName: string;

  @Property(String)
  @Required()
  lastName: string;

  @Property(String)
  @Required()
  password: string;

  @Property(Number)
  @Required()
  height: number;

  @Property(Number)
  @Required()
  weight: number;

  @Property(String)
  @Required()
  bloodType: string;

  @CollectionOf(() => ActivityModel)
  @Required()
  Activity: ActivityModel[];

  @CollectionOf(() => SleepModel)
  @Required()
  Sleep: SleepModel[];

  @CollectionOf(() => MedicationModel)
  @Required()
  Medication: MedicationModel[];
}

