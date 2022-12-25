import { Sleep } from "../client";
import { Integer, Required, Property, Format } from "@tsed/schema";
import { UserModel } from "./UserModel";

export class SleepModel implements Sleep {
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

  @Property(Number)
  @Integer()
  @Required()
  duration: number;

  @Property(Date)
  @Format("date-time")
  @Required()
  strat: Date;

  @Property(Date)
  @Format("date-time")
  @Required()
  end: Date;

  @Property(() => UserModel)
  @Required()
  user: UserModel;

  @Property(Number)
  @Integer()
  @Required()
  userId: number;
}

