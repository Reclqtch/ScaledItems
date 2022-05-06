package io.github.reclqtch.scaleditems;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class ScaledItemsTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transName, byte[] transClass) {
        if (!"net.minecraft.client.renderer.ItemRenderer".equals(transName)) return transClass;
        boolean success1 = false;
        try {
            ClassNode cn = new ClassNode();
            ClassReader cr = new ClassReader(transClass);
            cr.accept(cn, ClassReader.EXPAND_FRAMES);

            for (MethodNode method : cn.methods) {
                if (!"func_178096_b".equals(mapMethodName(cn, method))) continue;
                for (AbstractInsnNode insn : method.instructions.toArray()) {
                    if (insn.getOpcode() != Opcodes.RETURN) continue;
                    method.instructions.insertBefore(insn, new MethodInsnNode(Opcodes.INVOKESTATIC, "io/github/reclqtch/scaleditems/ScaledItems", "itemPreTransform", "()V", false));
                    success1 = true;
                    break;
                }
                break;
            }

            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            cn.accept(cw);
            return cw.toByteArray();
        } catch (NullPointerException e) {
            return transClass;
        } finally {
            System.out.println("ScaledItems > " + (success1 ? "Succe" : "Fail") + "ed Transforming " + transName);
        }

    }

    private static String mapMethodName(ClassNode classNode, MethodNode methodNode) {
        return FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(classNode.name, methodNode.name, methodNode.desc);
    }
}
