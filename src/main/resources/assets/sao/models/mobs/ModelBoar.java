// Date: 7/24/2014 3:03:58 AM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package assets.sao.models.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBoar extends ModelBase
{
  //fields
    ModelRenderer body_main;
    ModelRenderer body_back;
    ModelRenderer body_top;
    ModelRenderer body_front;
    ModelRenderer head_front;
    ModelRenderer head_side1;
    ModelRenderer head_side_2;
    ModelRenderer head_side11;
    ModelRenderer nose;
    ModelRenderer head_bottom;
    ModelRenderer nose_2;
    ModelRenderer body_front_21;
    ModelRenderer body_front_2;
    ModelRenderer horn_main;
    ModelRenderer Lhorn_1;
    ModelRenderer Lhorn;
    ModelRenderer Rhorn_;
    ModelRenderer Lear;
    ModelRenderer Rear;
    ModelRenderer body_back_2;
    ModelRenderer body_inside;
    ModelRenderer tail;
    ModelRenderer back_right_leg;
    ModelRenderer back_right_leg_2;
    ModelRenderer back_left_leg;
    ModelRenderer back_left_leg_2;
    ModelRenderer back_right_foot;
    ModelRenderer back_left_foot;
    ModelRenderer front_right_leg;
    ModelRenderer front_right_leg_2;
    ModelRenderer front_left_leg;
    ModelRenderer front_left_leg_2;
    ModelRenderer front_left_foot;
    ModelRenderer front_right_foot;
  
  public ModelBoar()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      body_main = new ModelRenderer(this, 0, 0);
      body_main.addBox(0F, 0F, 0F, 9, 11, 10);
      body_main.setRotationPoint(-2F, 0F, 0F);
      body_main.setTextureSize(64, 32);
      body_main.mirror = true;
      setRotation(body_main, 0F, 0F, 0F);
      body_back = new ModelRenderer(this, 0, 0);
      body_back.addBox(0F, 0F, 0F, 8, 7, 7);
      body_back.setRotationPoint(7F, 2F, 1.5F);
      body_back.setTextureSize(64, 32);
      body_back.mirror = true;
      setRotation(body_back, 0F, 0F, 0F);
      body_top = new ModelRenderer(this, 0, 0);
      body_top.addBox(0F, 0F, 0F, 6, 4, 7);
      body_top.setRotationPoint(0F, -4F, 1.5F);
      body_top.setTextureSize(64, 32);
      body_top.mirror = true;
      setRotation(body_top, 0F, 0F, 0F);
      body_front = new ModelRenderer(this, 0, 0);
      body_front.addBox(0F, 0F, 0F, 3, 6, 7);
      body_front.setRotationPoint(-5F, 2F, 1.5F);
      body_front.setTextureSize(64, 32);
      body_front.mirror = true;
      setRotation(body_front, 0F, 0F, 0F);
      head_front = new ModelRenderer(this, 0, 0);
      head_front.addBox(0F, 0F, 0F, 1, 6, 5);
      head_front.setRotationPoint(-5F, 3F, 2.5F);
      head_front.setTextureSize(64, 32);
      head_front.mirror = true;
      setRotation(head_front, 0F, 0F, 0.8922867F);
      head_side1 = new ModelRenderer(this, 0, 0);
      head_side1.addBox(0F, 0F, 0F, 4, 5, 1);
      head_side1.setRotationPoint(-5.5F, 3.5F, 2F);
      head_side1.setTextureSize(64, 32);
      head_side1.mirror = true;
      setRotation(head_side1, 0F, 0F, 0.8551081F);
      head_side_2 = new ModelRenderer(this, 0, 0);
      head_side_2.addBox(0F, 0F, 0F, 1, 1, 1);
      head_side_2.setRotationPoint(0F, 0F, 0F);
      head_side_2.setTextureSize(64, 32);
      head_side_2.mirror = true;
      setRotation(head_side_2, 0F, 0F, 0F);
      head_side1 = new ModelRenderer(this, 0, 0);
      head_side1.addBox(0F, 0F, 0F, 4, 5, 1);
      head_side1.setRotationPoint(-5.5F, 3.5F, 7F);
      head_side1.setTextureSize(64, 32);
      head_side1.mirror = true;
      setRotation(head_side1, 0F, 0F, 0.8551081F);
      nose = new ModelRenderer(this, 0, 0);
      nose.addBox(0F, 0F, 0F, 4, 3, 4);
      nose.setRotationPoint(-10F, 6.7F, 3F);
      nose.setTextureSize(64, 32);
      nose.mirror = true;
      setRotation(nose, 0F, 0F, 0F);
      head_bottom = new ModelRenderer(this, 0, 0);
      head_bottom.addBox(0F, 0F, 0F, 3, 1, 4);
      head_bottom.setRotationPoint(-7F, 9F, 3F);
      head_bottom.setTextureSize(64, 32);
      head_bottom.mirror = true;
      setRotation(head_bottom, 0F, 0F, -0.5948578F);
      nose_2 = new ModelRenderer(this, 0, 0);
      nose_2.addBox(0F, 0F, 0F, 2, 2, 2);
      nose_2.setRotationPoint(-12F, 7.7F, 4F);
      nose_2.setTextureSize(64, 32);
      nose_2.mirror = true;
      setRotation(nose_2, 0F, 0F, 0F);
      body_front_21 = new ModelRenderer(this, 0, 0);
      body_front_21.addBox(0F, 0F, 0F, 1, 5, 4);
      body_front_21.setRotationPoint(-6F, 9.5F, 3F);
      body_front_21.setTextureSize(64, 32);
      body_front_21.mirror = true;
      setRotation(body_front_21, 0F, 0F, -1.283798F);
      body_front_21 = new ModelRenderer(this, 0, 0);
      body_front_21.addBox(0F, 0F, 0F, 4, 4, 4);
      body_front_21.setRotationPoint(-5F, 5.5F, 3F);
      body_front_21.setTextureSize(64, 32);
      body_front_21.mirror = true;
      setRotation(body_front_21, 0F, 0F, 0.1858931F);
      horn_main = new ModelRenderer(this, 0, 0);
      horn_main.addBox(0F, 0F, 0F, 1, 4, 7);
      horn_main.setRotationPoint(0F, -4F, 1.5F);
      horn_main.setTextureSize(64, 32);
      horn_main.mirror = true;
      setRotation(horn_main, 0F, 0F, 0.2602503F);
      Lhorn_1 = new ModelRenderer(this, 0, 0);
      Lhorn_1.addBox(0F, 0F, 0F, 1, 1, 6);
      Lhorn_1.setRotationPoint(-11.5F, 8F, 2F);
      Lhorn_1.setTextureSize(64, 32);
      Lhorn_1.mirror = true;
      setRotation(Lhorn_1, 0F, 0F, 0F);
      Lhorn = new ModelRenderer(this, 0, 0);
      Lhorn.addBox(0F, 0F, 0F, 1, 3, 1);
      Lhorn.setRotationPoint(-11.5F, 6F, 6.7F);
      Lhorn.setTextureSize(64, 32);
      Lhorn.mirror = true;
      setRotation(Lhorn, 0.185895F, 0F, 0F);
      Rhorn_ = new ModelRenderer(this, 0, 0);
      Rhorn_.addBox(0F, 0F, 0F, 1, 3, 1);
      Rhorn_.setRotationPoint(-11.5F, 5.6F, 2.3F);
      Rhorn_.setTextureSize(64, 32);
      Rhorn_.mirror = true;
      setRotation(Rhorn_, -0.1858903F, 0F, 0F);
      Lear = new ModelRenderer(this, 0, 0);
      Lear.addBox(0F, 0F, 0F, 1, 4, 1);
      Lear.setRotationPoint(-6.5F, 2F, 9F);
      Lear.setTextureSize(64, 32);
      Lear.mirror = true;
      setRotation(Lear, -0.8551081F, 0F, 0F);
      Rear = new ModelRenderer(this, 0, 0);
      Rear.addBox(0F, 0F, 0F, 1, 4, 1);
      Rear.setRotationPoint(-6.5F, 3F, 0.5F);
      Rear.setTextureSize(64, 32);
      Rear.mirror = true;
      setRotation(Rear, 0.8551066F, 0F, 0F);
      body_back_2 = new ModelRenderer(this, 0, 0);
      body_back_2.addBox(0F, 0F, 0F, 1, 8, 7);
      body_back_2.setRotationPoint(5F, -3.4F, 1.5F);
      body_back_2.setTextureSize(64, 32);
      body_back_2.mirror = true;
      setRotation(body_back_2, 0F, 0F, -0.6320364F);
      body_inside = new ModelRenderer(this, 0, 0);
      body_inside.addBox(0F, 0F, 0F, 7, 2, 7);
      body_inside.setRotationPoint(5.5F, -3F, 1.5F);
      body_inside.setTextureSize(64, 32);
      body_inside.mirror = true;
      setRotation(body_inside, 0F, 0F, 0.9666436F);
      tail = new ModelRenderer(this, 0, 0);
      tail.addBox(0F, 0F, 0F, 1, 7, 1);
      tail.setRotationPoint(14F, 4F, 4.5F);
      tail.setTextureSize(64, 32);
      tail.mirror = true;
      setRotation(tail, 0F, 0F, -0.7063936F);
      back_right_leg = new ModelRenderer(this, 0, 0);
      back_right_leg.addBox(0F, 0F, 0F, 3, 7, 1);
      back_right_leg.setRotationPoint(11F, 5F, 2F);
      back_right_leg.setTextureSize(64, 32);
      back_right_leg.mirror = true;
      setRotation(back_right_leg, -0.4833219F, 0F, -0.2974289F);
      back_right_leg_2 = new ModelRenderer(this, 0, 0);
      back_right_leg_2.addBox(0F, 0F, 0F, 2, 5, 1);
      back_right_leg_2.setRotationPoint(13F, 10F, -0.7F);
      back_right_leg_2.setTextureSize(64, 32);
      back_right_leg_2.mirror = true;
      setRotation(back_right_leg_2, 0F, 0F, 0.2230717F);
      back_left_leg = new ModelRenderer(this, 0, 0);
      back_left_leg.addBox(0F, 0F, 0F, 3, 8, 1);
      back_left_leg.setRotationPoint(11F, 5F, 7F);
      back_left_leg.setTextureSize(64, 32);
      back_left_leg.mirror = true;
      setRotation(back_left_leg, 0.4833166F, 0F, -0.2974216F);
      back_left_leg_2 = new ModelRenderer(this, 0, 0);
      back_left_leg_2.addBox(0F, 0F, 0F, 2, 5, 1);
      back_left_leg_2.setRotationPoint(13F, 10F, 9.9F);
      back_left_leg_2.setTextureSize(64, 32);
      back_left_leg_2.mirror = true;
      setRotation(back_left_leg_2, 0F, 0F, 0.2230705F);
      back_right_foot = new ModelRenderer(this, 0, 0);
      back_right_foot.addBox(0F, 0F, 0F, 3, 1, 2);
      back_right_foot.setRotationPoint(11F, 15F, -1F);
      back_right_foot.setTextureSize(64, 32);
      back_right_foot.mirror = true;
      setRotation(back_right_foot, 0F, 0F, 0F);
      back_left_foot = new ModelRenderer(this, 0, 0);
      back_left_foot.addBox(0F, 0F, 0F, 3, 1, 2);
      back_left_foot.setRotationPoint(11F, 15F, 9.3F);
      back_left_foot.setTextureSize(64, 32);
      back_left_foot.mirror = true;
      setRotation(back_left_foot, 0F, 0F, 0F);
      front_right_leg = new ModelRenderer(this, 0, 0);
      front_right_leg.addBox(0F, 0F, 0F, 5, 1, 1);
      front_right_leg.setRotationPoint(1F, 9F, -1F);
      front_right_leg.setTextureSize(64, 32);
      front_right_leg.mirror = true;
      setRotation(front_right_leg, 0F, 0F, 1.375609F);
      front_right_leg_2 = new ModelRenderer(this, 0, 0);
      front_right_leg_2.addBox(0F, 0F, 0F, 3, 7, 1);
      front_right_leg_2.setRotationPoint(1F, 8F, -1F);
      front_right_leg_2.setTextureSize(64, 32);
      front_right_leg_2.mirror = true;
      setRotation(front_right_leg_2, 0F, 0F, 0F);
      front_left_leg = new ModelRenderer(this, 0, 0);
      front_left_leg.addBox(0F, 0F, 0F, 5, 1, 1);
      front_left_leg.setRotationPoint(1F, 9F, 10F);
      front_left_leg.setTextureSize(64, 32);
      front_left_leg.mirror = true;
      setRotation(front_left_leg, 0F, 0F, 1.37614F);
      front_left_leg_2 = new ModelRenderer(this, 0, 0);
      front_left_leg_2.addBox(0F, 0F, 0F, 3, 7, 1);
      front_left_leg_2.setRotationPoint(1F, 8F, 10F);
      front_left_leg_2.setTextureSize(64, 32);
      front_left_leg_2.mirror = true;
      setRotation(front_left_leg_2, 0F, 0F, 0F);
      front_left_foot = new ModelRenderer(this, 0, 0);
      front_left_foot.addBox(0F, 0F, 0F, 2, 1, 1);
      front_left_foot.setRotationPoint(2F, 15F, 10F);
      front_left_foot.setTextureSize(64, 32);
      front_left_foot.mirror = true;
      setRotation(front_left_foot, 0F, 0F, 0F);
      front_right_foot = new ModelRenderer(this, 0, 0);
      front_right_foot.addBox(0F, 0F, 0F, 2, 1, 1);
      front_right_foot.setRotationPoint(2F, 15F, -1F);
      front_right_foot.setTextureSize(64, 32);
      front_right_foot.mirror = true;
      setRotation(front_right_foot, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    body_main.render(f5);
    body_back.render(f5);
    body_top.render(f5);
    body_front.render(f5);
    head_front.render(f5);
    head_side1.render(f5);
    head_side_2.render(f5);
    head_side1.render(f5);
    nose.render(f5);
    head_bottom.render(f5);
    nose_2.render(f5);
    body_front_21.render(f5);
    body_front_21.render(f5);
    horn_main.render(f5);
    Lhorn_1.render(f5);
    Lhorn.render(f5);
    Rhorn_.render(f5);
    Lear.render(f5);
    Rear.render(f5);
    body_back_2.render(f5);
    body_inside.render(f5);
    tail.render(f5);
    back_right_leg.render(f5);
    back_right_leg_2.render(f5);
    back_left_leg.render(f5);
    back_left_leg_2.render(f5);
    back_right_foot.render(f5);
    back_left_foot.render(f5);
    front_right_leg.render(f5);
    front_right_leg_2.render(f5);
    front_left_leg.render(f5);
    front_left_leg_2.render(f5);
    front_left_foot.render(f5);
    front_right_foot.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
  }

}