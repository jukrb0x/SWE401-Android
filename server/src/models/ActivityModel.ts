import { Activity } from "../client";
import { Integer, Required, Property, Format, Enum } from "@tsed/schema";
import { ActivityType } from "../enums";
import { UserModel } from "./UserModel";

export class ActivityModel implements Activity {
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
  type: string;

  @Property(Date)
  @Format("date-time")
  @Required()
  startAt: Date;

  @Property(Number)
  @Integer()
  @Required()
  duration: number;

  @Required()
  @Enum(ActivityType)
  activityType: ActivityType;

  @Property(() => UserModel)
  @Required()
  user: UserModel;

  @Property(Number)
  @Integer()
  @Required()
  userId: number;
}

