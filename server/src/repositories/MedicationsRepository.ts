import { isArray } from "@tsed/core";
import { deserialize } from "@tsed/json-mapper";
import { Injectable, Inject } from "@tsed/di";
import { PrismaService } from "../services/PrismaService";
import { Prisma, Medication } from "../client";
import { MedicationModel } from "../models";

@Injectable()
export class MedicationsRepository {
  @Inject()
  protected prisma: PrismaService;

  get collection() {
    return this.prisma.medication
  }

  get groupBy() {
    return this.collection.groupBy.bind(this.collection)
  }

  protected deserialize<T>(obj: null | Medication | Medication[]): T {
    return deserialize<T>(obj, { type: MedicationModel, collectionType: isArray(obj) ? Array : undefined })
  }

  async findUnique(args: Prisma.MedicationFindUniqueArgs): Promise<MedicationModel | null> {
    const obj = await this.collection.findUnique(args);
    return this.deserialize<MedicationModel | null>(obj);
  }

  async findFirst(args: Prisma.MedicationFindFirstArgs): Promise<MedicationModel | null> {
    const obj = await this.collection.findFirst(args);
    return this.deserialize<MedicationModel | null>(obj);
  }

  async findMany(args?: Prisma.MedicationFindManyArgs): Promise<MedicationModel[]> {
    const obj = await this.collection.findMany(args);
    return this.deserialize<MedicationModel[]>(obj);
  }

  async create(args: Prisma.MedicationCreateArgs): Promise<MedicationModel> {
    const obj = await this.collection.create(args);
    return this.deserialize<MedicationModel>(obj);
  }

  async update(args: Prisma.MedicationUpdateArgs): Promise<MedicationModel> {
    const obj = await this.collection.update(args);
    return this.deserialize<MedicationModel>(obj);
  }

  async upsert(args: Prisma.MedicationUpsertArgs): Promise<MedicationModel> {
    const obj = await this.collection.upsert(args);
    return this.deserialize<MedicationModel>(obj);
  }

  async delete(args: Prisma.MedicationDeleteArgs): Promise<MedicationModel> {
    const obj = await this.collection.delete(args);
    return this.deserialize<MedicationModel>(obj);
  }

  async deleteMany(args: Prisma.MedicationDeleteManyArgs) {
    return this.collection.deleteMany(args)
  }

  async updateMany(args: Prisma.MedicationUpdateManyArgs) {
    return this.collection.updateMany(args)
  }

  async aggregate(args: Prisma.MedicationAggregateArgs) {
    return this.collection.aggregate(args)
  }
}
