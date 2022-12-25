import { isArray } from "@tsed/core";
import { deserialize } from "@tsed/json-mapper";
import { Injectable, Inject } from "@tsed/di";
import { PrismaService } from "../old-services/PrismaService";
import { Prisma, MedicationLog } from "../client";
import { MedicationLogModel } from "../models";

@Injectable()
export class MedicationLogsRepository {
  @Inject()
  protected prisma: PrismaService;

  get collection() {
    return this.prisma.medicationLog
  }

  get groupBy() {
    return this.collection.groupBy.bind(this.collection)
  }

  protected deserialize<T>(obj: null | MedicationLog | MedicationLog[]): T {
    return deserialize<T>(obj, { type: MedicationLogModel, collectionType: isArray(obj) ? Array : undefined })
  }

  async findUnique(args: Prisma.MedicationLogFindUniqueArgs): Promise<MedicationLogModel | null> {
    const obj = await this.collection.findUnique(args);
    return this.deserialize<MedicationLogModel | null>(obj);
  }

  async findFirst(args: Prisma.MedicationLogFindFirstArgs): Promise<MedicationLogModel | null> {
    const obj = await this.collection.findFirst(args);
    return this.deserialize<MedicationLogModel | null>(obj);
  }

  async findMany(args?: Prisma.MedicationLogFindManyArgs): Promise<MedicationLogModel[]> {
    const obj = await this.collection.findMany(args);
    return this.deserialize<MedicationLogModel[]>(obj);
  }

  async create(args: Prisma.MedicationLogCreateArgs): Promise<MedicationLogModel> {
    const obj = await this.collection.create(args);
    return this.deserialize<MedicationLogModel>(obj);
  }

  async update(args: Prisma.MedicationLogUpdateArgs): Promise<MedicationLogModel> {
    const obj = await this.collection.update(args);
    return this.deserialize<MedicationLogModel>(obj);
  }

  async upsert(args: Prisma.MedicationLogUpsertArgs): Promise<MedicationLogModel> {
    const obj = await this.collection.upsert(args);
    return this.deserialize<MedicationLogModel>(obj);
  }

  async delete(args: Prisma.MedicationLogDeleteArgs): Promise<MedicationLogModel> {
    const obj = await this.collection.delete(args);
    return this.deserialize<MedicationLogModel>(obj);
  }

  async deleteMany(args: Prisma.MedicationLogDeleteManyArgs) {
    return this.collection.deleteMany(args)
  }

  async updateMany(args: Prisma.MedicationLogUpdateManyArgs) {
    return this.collection.updateMany(args)
  }

  async aggregate(args: Prisma.MedicationLogAggregateArgs) {
    return this.collection.aggregate(args)
  }
}
